package safeHub

import enums.StatusEffect
import units.Team
import units.enemies.BossMaledicto
import units.enemies.Humanoid
import utils.devider

class Arena(
    var ownTeam: Team,

    ) {


    var enemyTeamLevel1: Team = Team(mutableListOf(zombieLevel1, orcLevel1, dragonLevel1))

    var enemyTeamLevel5: Team = Team(mutableListOf(orcLevel5, dragonLevel5, undeadLevel5))

    val enemyTeamBoss = Team(mutableListOf(BossMaledicto("Maledicto", 50, 4, 6, 3, 10, ownTeam)))




    fun startArena() {
        devider()
        println("Your first opponents are a group of orcs! Show us your skills!")
        if (Battle(ownTeam, enemyTeamLevel1).fight()) {
            ownTeam.takeReward(100, 100)
        } else {
            devider()
            println("Pitiful!! I hope your next fight will be more entertaining! (You are being transported to the Safe Hub")
            resetPlayerAfterFight()
            SafeHub(ownTeam)
        }
        if (Battle(ownTeam, enemyTeamLevel5).fight()) {
            ownTeam.takeReward(200, 250)
        } else {
            devider()
            println("Pitiful!! I hope your next fight will be more entertaining! (You are being transported to the Safe Hub")
            resetPlayerAfterFight()
            SafeHub(ownTeam)

        }
        if (Battle(ownTeam, enemyTeamBoss).fight()) {
            ownTeam.takeReward(500, 500)
        } else {
            devider()
            println("Pitiful!! I hope your next fight will be more entertaining! (You are being transported to the Safe Hub")
            resetPlayerAfterFight()
            SafeHub(ownTeam)

        }
    resetPlayerAfterFight()

    }
    private fun resetPlayerAfterFight() {

        this.ownTeam.members.forEach {
            it.curHp = it.maxHp
            it.mana = 0
            it.isDead = false
            if (it.statusEffects!!.contains(StatusEffect.BlessingOfMaledicto)) {
                it.statusEffects!!.clear()
                it.statusEffects!!.add(StatusEffect.BlessingOfMaledicto)
            } else {
                it.statusEffects!!.clear()
            }

        }

    }
}