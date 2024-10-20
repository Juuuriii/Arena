package abilities

import utils.displayManaCost
import enums.*
import item.Item
import item.Potion
import units.PlayerChar
import units.enemies.Enemy
import utils.*


open class Ability(

    var name: String,
    private var dmgOrHeal: Int,
    val type: AbilityType,
    var cost: Int,
    var generate: Int,
    private var causesStatus: StatusEffect?,
    private var description: String = "",
    var amountTargets: Int = 1,  // should be only: 1 , 3 or 6

) {

    fun use(user: PlayerChar, targets: MutableList<PlayerChar>) {
        var paint1: String
        var paint2: String
        if (this.generate > 0) {
            user.mana += this.generate
        }
        if (this.cost > 0) {
            user.mana -= this.cost
        }
        if (this.causesStatus != null) {
            targets.forEach {
                if (!it.statusEffects?.contains(causesStatus)!!) {
                    it.statusEffects?.add(causesStatus!!)
                }
            }
        }

        when (this.type) {

            AbilityType.Heal -> {

                targets.forEach {
                    if (it is Enemy){
                        paint1 = BRIGHTRED

                    } else {
                        paint1 = BLUE

                    }
                    if (user is Enemy){
                        paint2 = BRIGHTRED
                    } else {
                        paint2 = BLUE
                    }
                    var healAmount = this.dmgOrHeal + user.spellPwr
                    if (it.type == EnemyType.Undead) {
                        healAmount *= 2
                    }
                    if (it.isDead) {
                        println()
                        println("${it.name.color(paint1)} is already defeated")
                    } else {
                        println()
                        println(
                            "${user.name.color(paint2)} heals ${it.name.color(paint1)} for $healAmount HP with ${this.name.color(
                                BRIGHTWHITE)}"
                        )
                        it.takeHeal(healAmount)

                    }
                }

            }

            AbilityType.Summon -> {
                if (user is Enemy){
                    paint2 = BRIGHTRED
                } else {
                    paint2 = BLUE
                }
                val summon = user.listOfSummons.random()
                targets.add(summon)
                println()
                println("${user.name.color(paint2)} summoned ${summon.name.color(paint2)}")
            }

            AbilityType.Attack -> {

                targets.forEach {
                    if(it is Enemy) {
                        paint1 = BRIGHTRED
                        paint2 = BLUE
                    } else {
                        paint1 = BLUE
                        paint2 = BRIGHTRED
                    }
                    var damageAmount = this.dmgOrHeal + user.attackPwr - it.armor
                    if (damageAmount <= 0) {
                        damageAmount = 1
                    }
                    if (it.isDead) {
                        println()
                        println("${it.name.color(paint1)} is already defeated")
                    } else {
                        println()
                        println(
                            "${user.name.color(paint2)} deals $damageAmount damage to ${it.name.color(paint1)} with ${this.name.color(
                                BRIGHTWHITE)}"
                        )
                        it.takeDamage(damageAmount)
                    }
                }

            }

            else -> {
                targets.forEach {
                    if(it is Enemy) {
                        paint1 = BRIGHTRED
                        paint2 = BLUE
                    } else {
                        paint1 = BLUE
                        paint2 = BRIGHTRED
                    }
                    var damageAmount = this.dmgOrHeal + user.spellPwr
                    if (damageAmount <= 0) {
                        damageAmount = 1
                    }

                    if (it.isDead) {
                        println()
                        println("${it.name.color(paint1)} is already defeated")
                    } else {
                        println()
                        println(
                            "${user.name.color(paint2)} deals $damageAmount damage to ${it.name.color(paint1)} with ${this.name.color(
                                BRIGHTWHITE)}"
                        )
                        it.takeDamage(damageAmount)
                    }
                }
            }

        }
    }

    fun use(user: PlayerChar, item: Item) {

        if (item is Potion) {
            item.use(user)
        } else {
            println("Damn")
        }

    }

    fun info(): String {
        return if (this.type != AbilityType.Heal) {
            println()
            this.name.padEnd(15, ' ') + "Type: ${this.type.name}".padEnd(
                18,
                ' '
            ) + displayAmountOfTargets(this) + "Base Damage: ${this.dmgOrHeal}".padEnd(
                20,
                ' '
            ) + displayManaCost(this) + this.description
        } else {
            println()
            this.name.padEnd(15, ' ') + "Type: ${this.type.name}".padEnd(
                18,
                ' '
            ) + displayAmountOfTargets(this) + "Base Healing: ${this.dmgOrHeal}".padEnd(
                20,
                ' '
            ) + displayManaCost(this) + this.description
        }

    }

}