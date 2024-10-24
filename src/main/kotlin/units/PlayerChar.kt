package units

import utils.*
import abilities.*
import enums.*
import item.*


open class PlayerChar(
    var name: String,
    var maxHp: Int,
    var spellPwr: Int,
    var attackPwr: Int,
    var armor: Int,
    var type: EnemyType,
    var level: Int,
    var teamInventory: Inventory = Inventory()
) {


// hello
    var exp = 0

    var poisoned = 3
    var curHp = maxHp
        get() {
            return if (field < 0) {
                0
            } else if (field > maxHp) {
                maxHp
            } else {
                field
            }
        }

    var isDead = false

    var mana = 0
        get() {
            return if (field < 0) {
                0
            } else {
                field
            }
        }

    var abilityList: MutableList<Ability> = mutableListOf()

    var statusEffects: MutableList<StatusEffect>? = mutableListOf()

    var usingItem: MutableList<Item> = mutableListOf()

    var listOfSummons: MutableList<PlayerChar> = mutableListOf()

    fun printAbilities() {

        this.abilityList.forEachIndexed { i, Ability ->
            if (Ability.type == AbilityType.Item) {
                println()
                println("${i + 1}) ${Ability.name} (Amount of usable Items: ${this.teamInventory.inventory.filterIsInstance<Potion>().size})")
            } else {
                println("${i + 1}) ${Ability.info()}")
            }
        }
    }

    open fun pickAbility(): Ability {
        devider()
        println(this.name)
        println()
        println(
            "Status:".padEnd(10) + displayHpBar(
                this.curHp,
                this.maxHp
            ) + displayManaBar(this.mana) + displayStatusEffects(this.statusEffects)
        )
        println()
        println("Abilities:")
        printAbilities()
        println()
        println("Enter the number of the Action you want to take:")


        return try {
            val input = readln().toInt()
            var pickedAbility = this.abilityList[input - 1]

            if (pickedAbility.type == AbilityType.Item) {
                if (this.teamInventory.inventory.isEmpty()) {
                    devider()
                    println("You do not have any usable items at the moment")
                    throw Exception()
                } else {
                    usingItem.add(this.teamInventory.pickUsables())
                }
            }
            if (pickedAbility.cost > this.mana) {
                devider()
                println("Not Enough Mana[" + "*".color(BLUE) + "]")
                this.pickAbility()
            }
            pickedAbility

        } catch (e: Exception) {
            devider()
            println("Not a valid input. Please try again.")
            this.pickAbility()
        }

    }

    open fun pickTarget(ownTeam: Team, enemyTeam: Team, pickedAbility: Ability): MutableList<PlayerChar> {

        var finalTargets: MutableList<PlayerChar> = mutableListOf()

        var targetAll: MutableList<PlayerChar> = mutableListOf()

        targetAll.addAll(ownTeam.members)
        targetAll.addAll(enemyTeam.members)

        when (pickedAbility.type) {

            AbilityType.Heal -> {
                if (pickedAbility.amountTargets == 1) {
                    targetAll.forEachIndexed { index, playerChar ->
                        if (index == 3) {
                            println("-----------------------")
                        }
                        println("${index + 1}) " + playerChar.printLife())
                    }
                    println()
                    println("Use ${pickedAbility.name} on?")
                    println()
                    println("Enter the number of your target:")
                    while (finalTargets.size < pickedAbility.amountTargets) {
                        try {
                            val input = readln().toInt()
                            if (targetAll[input - 1].isDead) {
                                devider()
                                println("Your selected target is already defeated. Select a different target:")
                                continue
                            }
                            finalTargets.add(targetAll[input - 1])
                        } catch (e: Exception) {
                            devider()
                            println("Not a valid input. Please try again.")
                        }

                    }
                } else if (pickedAbility.amountTargets == 3) {


                    try {
                        var input: Int = 0
                        while (input != 1 && input != 2) {
                            devider()
                            println("Your Team:")

                            printTeam(ownTeam)
                            println("Enemies:")

                            printTeam(enemyTeam)

                            println(
                                """
                        Use ${pickedAbility.name} on:
                        
                            1) Own Team
                            2) Enemies
                          
                    """.trimIndent()
                            )
                            println("Enter the number for your target team (1 or 2):")
                            input = readln().toInt()

                            when (input) {
                                1 -> {
                                    ownTeam.members.filter { !it.isDead }.forEach { finalTargets.add(it) }
                                }

                                2 -> {
                                    enemyTeam.members.filter { !it.isDead }.forEach { finalTargets.add(it) }
                                }

                                else -> {
                                    devider()
                                    println("Not a valid input. Please try again.")
                                }
                            }
                        }
                    } catch (e: Exception) {
                        println("Not a valid input. Please try again.")
                        pickTarget(ownTeam, enemyTeam, pickedAbility)
                    }


                } else {
                    targetAll.filter { !it.isDead }.forEach { finalTargets.add(it) }
                }
            }


            AbilityType.Summon -> {
                return ownTeam.members
            }

            else -> {
                if (pickedAbility.amountTargets == 1) {
                    while (finalTargets.size < pickedAbility.amountTargets) {
                        devider()
                        println("Use ${pickedAbility.name} on?")
                        printTeam(enemyTeam)
                        println("Enter the number of your target:")
                        try {
                            val input = readln().toInt()
                            if (enemyTeam.members[input - 1].isDead) {
                                println("Your selected target is already defeated. Select a different target:")
                                continue
                            }
                            finalTargets.add(enemyTeam.members[input - 1])
                        } catch (e: Exception) {
                            devider()
                            println("Not a valid input. Please try again")

                            continue
                        }
                    }
                } else if (pickedAbility.amountTargets == 3) {
                    enemyTeam.members.filter { !it.isDead }.forEach {
                        finalTargets.add(it)
                    }

                } else {

                    targetAll.filter { !it.isDead }.forEach {
                        finalTargets.add(it)
                    }
                }
            }
        }
        return finalTargets
    }

    fun printLife(): String {

        return if (this.isDead) {
            this.name.padEnd(15, ' ') + displayDead(this.isDead)
        } else {

            this.name.padEnd(15, ' ') + "Lvl. ${this.level}".padEnd(10) + displayHpBar(
                curHp,
                maxHp
            ) + displayManaBar(this.mana) + displayStatusEffects(statusEffects)
        }
    }

    fun takeDamage(amount: Int) {
        this.curHp -= amount
        if (this.curHp <= 0) {
            this.isDead = true
            this.statusEffects!!.clear()
            println()
            println("${this.name} was Defeated")
        }
    }

    open fun takeHeal(amount: Int) {
        this.curHp += amount
    }

    fun statusEffectAction() {
        if (this.statusEffects!!.contains(StatusEffect.Poisoned)) {
            println()
            println("${this.name} takes 2 damage from ${StatusEffect.Poisoned.statusName}")
            this.takeDamage(2)
            this.poisoned-=1
            if (this.poisoned == 0) {
                this.statusEffects!!.remove(StatusEffect.Poisoned)
                println()
                println("${this.name} is no longer ${StatusEffect.Poisoned.name.color(BRIGHTGREEN)}")
                this.poisoned = 3
            }
            if (this.statusEffects!!.contains(StatusEffect.Frozen)) {
                println()
                println("${this.name} is ${StatusEffect.Frozen.statusName}")
            }

        }
        if (this.statusEffects!!.contains(StatusEffect.CurseOfMaledicto)){
            if (this.maxHp <= 10) {

                this.takeDamage(1)
                println()
                println("${this.name} takes 1 damage from ${StatusEffect.CurseOfMaledicto.statusName}")

            } else {
                var damage = (maxHp * 0.1).toInt()
                this.takeDamage(damage)
                println()
                println("${this.name} takes $damage damage from ${StatusEffect.CurseOfMaledicto.statusName}")
            }
            if (this.curHp < this.maxHp * 0.2 && !isDead) {
                this.statusEffects!!.remove(StatusEffect.CurseOfMaledicto)
                println()
                println("${this.name} is no longer cursed by ${StatusEffect.CurseOfMaledicto.statusName}")
            }
        }
        if (this.statusEffects!!.contains(StatusEffect.BlessingOfMaledicto)){
            val amount = (this.maxHp * 0.1).toInt()
            this.takeHeal(amount)
            println()
            println("${this.name} regenerates $amount Hp from ${StatusEffect.BlessingOfMaledicto.statusName}")
        }
        /*var iterator = this.statusEffects!!.iterator()

        for (statusEffect in iterator) {


            when (statusEffect) {

                StatusEffect.Poisoned -> {
                    this.takeDamage(2)
                    println()
                    println("${this.name} takes 2 damage from ${StatusEffect.Poisoned.statusName}")
                    this.poisoned--
                    if (this.poisoned == 0) {
                        iterator.remove()
                        println()
                        println("${this.name} is no longer ${StatusEffect.Poisoned.name.color(BRIGHTGREEN)}")
                        this.poisoned = 3
                    }

                }//TODO()

                StatusEffect.Frozen -> {
                    println()
                    println("${this.name} is ${StatusEffect.Frozen.statusName}")
                }

                StatusEffect.Burning -> TODO()
                StatusEffect.CurseOfMaledicto -> {

                    if (this.maxHp <= 10) {

                        this.takeDamage(1)
                        println()
                        println("${this.name} takes 1 damage from ${StatusEffect.CurseOfMaledicto.statusName}")

                    } else {
                        var damage = (maxHp * 0.1).toInt()
                        this.takeDamage(damage)
                        println()
                        println("${this.name} takes $damage damage from ${StatusEffect.CurseOfMaledicto.statusName}")
                    }
                    if (this.curHp < this.maxHp * 0.2 && !isDead) {
                        iterator.remove()
                        println()
                        println("${this.name} is no longer cursed by ${StatusEffect.CurseOfMaledicto.statusName}")
                    }
                }

                StatusEffect.BlessingOfMaledicto -> {
                    val amount = (this.maxHp * 0.1).toInt()
                    this.takeHeal(amount)
                    println()
                    println("${this.name} regenerates $amount Hp from ${StatusEffect.BlessingOfMaledicto.statusName}")
                }
            }

        }*/
    }

    fun gainExp(exp: Int) {
        this.exp += exp
        devider()
        println("${this.name} gained $exp Experience")
        if (exp >= level * 100) {
            devider()
            println("${this.name} leveled up!")
            this.exp -= level * 100

            this.levelUp()
        }

    }

    fun levelUp() {
        this.level++
        var skillpoint = 1
        this.printLife()
        println(
            """
            
            Which stat would you like to increase?($skillpoint pick left) 
            
             1) HP              +2
             2) Attack Power    +1
             3) Spell Power     +1
             4) Armor           +1
            
            Enter the number of the Stat you want to increase:
        """.trimIndent()
        )
        while (skillpoint != 0) {
            when (readln()) {

                "1" -> {
                    this.maxHp += 2
                    this.curHp += 2
                    println()
                    println("HP of ${this.name} was increased by 2")
                    skillpoint--
                }
                "2" -> {
                    this.attackPwr += 1
                    println()
                    println("Attack Power of ${this.name} was increased by 1")
                    skillpoint--
                }
                "3" -> {
                    this.spellPwr += 1
                    println()
                    println("Spell Power of ${this.name} was increased by 1")
                    skillpoint--
                }
                "4" -> {
                    this.armor += 1
                    println()
                    println("Armor of ${this.name} was increased by 1")
                }
                else -> {
                    println()
                    println("Not a valid input. Please try again:")
                    continue
                }
            }


        }
    }

    fun save(): String{
        return "${this::class.simpleName}_${this.name}_${this.maxHp}_${this.spellPwr}_${this.attackPwr}_${this.armor}_${this.level}_${this.exp}"
    }

    fun saveInventory(): String {
        var inventory: String = "${this.teamInventory::class.simpleName}_"




        return inventory
    }
}
