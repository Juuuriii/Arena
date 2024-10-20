package safeHub

import utils.devider
import utils.pressEnterToContinue
import abilities.Ability
import enums.AbilityType
import enums.StatusEffect
import units.PlayerChar
import units.Team
import utils.printTeam

class Battle(
    private var ownTeam: Team,
    private var enemyTeam: Team,
) {




    fun fight(): Boolean {
        devider()
        println("FIGHT FOR HONOR AND GLORY!")
        var roundCounter = 1

        while (enemyTeam.isTeamDead() && ownTeam.isTeamDead()) {
            devider()
            println("Enemies:")
            printTeam(this.enemyTeam)
            println("Your Team:")
            printTeam(this.ownTeam)
            pressEnterToContinue()

            val fightSequence: MutableList<Triple<PlayerChar, Ability, MutableList<PlayerChar>>> = mutableListOf()


            devider()
            println("ROUND $roundCounter")

            ownTeam.members.forEach { user ->
                if (!user.isDead) {

                    val pickedAbility = user.pickAbility()
                    var pickedTarget = mutableListOf<PlayerChar>()
                    if (pickedAbility.type != AbilityType.Item) {
                        pickedTarget = user.pickTarget(ownTeam, enemyTeam, pickedAbility)
                    }


                    fightSequence.add(Triple(user, pickedAbility, pickedTarget))

                }

            }

            enemyTeam.members.forEach {
                if (!it.isDead) {
                    val user = it
                    val pickedAbility = it.pickAbility()
                    val pickedTarget = it.pickTarget(ownTeam, enemyTeam, pickedAbility)
                    fightSequence.add(Triple(user, pickedAbility, pickedTarget))
                }
            }
            devider()
            println("Fight:")
            fightSequence.filter { !it.first.isDead }.forEach { (user, ability, target) ->

                if (user.isDead) {
                    println()
                    println("${user.name} is defeated and can not attack")
                } else {

                    val frozenChance = (1..100).random()

                    if (user.statusEffects!!.contains(StatusEffect.Frozen) && frozenChance < 50) {

                        println()
                        println("${user.name} is ${StatusEffect.Frozen.statusName} and can't move!")
                    } else {
                        if (user.statusEffects!!.contains(StatusEffect.Frozen)) {
                            println()
                            println("${user.name} broke free and is no longer ${StatusEffect.Frozen.statusName}")
                            user.statusEffects!!.remove(StatusEffect.Frozen)
                        }
                        if (ability.type == AbilityType.Item) {

                            ability.use(user, user.usingItem.first())
                            Thread.sleep(500)
                        } else {
                            ability.use(user, target)
                            Thread.sleep(500)
                        }
                    }
                }
            }

            if(ownTeam.members.any { it.statusEffects!!.isNotEmpty() } || enemyTeam.members.any{ it.statusEffects!!.isNotEmpty()}) {
                devider()

                println("Status Effects:")
                ownTeam.members.forEach {
                    if (!it.isDead) {
                        it.statusEffectAction()
                    }
                }
                enemyTeam.members.forEach {
                    if (!it.isDead) {
                        it.statusEffectAction()
                    }
                }
            }

            roundCounter += 1

        }
        return if (this.ownTeam.isTeamDead()) {
            devider()
            println("All enemies are defeated! You Won!!!")
            resetEnemyAfterFight()
            true

        } else {
            devider()
            println("All of your heroes are defeated! You Lost!!!")

            resetEnemyAfterFight()
            false

        }


    }


    private fun resetEnemyAfterFight(){
        this.enemyTeam.members.forEach {
            it.curHp = it.maxHp
            it.mana = 0
            it.isDead = false
        }
    }

}