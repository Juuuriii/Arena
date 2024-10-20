package units.enemies

import units.Team
import abilities.*
import enums.EnemyType
import enums.StatusEffect

class BossMaledicto(
    name: String,
    maxHp: Int,
    spellPwr: Int,
    attackPwr: Int,
    armor: Int,
    level: Int = 1,
    var ownTeam: Team



    ) : Enemy(name, maxHp, spellPwr, attackPwr, armor, type = EnemyType.Humanoid, level) {

    var summonedHelper = false

    var summon1 = UndeadKnight("Undead Knight", 28, 4, 4, 2)
    init {
        this.abilityList.add(curseOfMaledicto)
        this.abilityList.add(frostBoltBoss)
        this.abilityList.add(miasma)
        this.abilityList.add(unholyStrike)
        this.abilityList.add(deathStrike)
        this.abilityList.add(summonUndeadKnight)
        this.statusEffects!!.add(StatusEffect.BlessingOfMaledicto)

        this.listOfSummons.add(
            summon1
        )

    }

    override fun pickAbility(): Ability {

        if ((this.curHp <= (this.maxHp/2)) && !summonedHelper) {
            summonedHelper = true
            return summonUndeadKnight
        }

        return if (this.ownTeam.members.all { !it.statusEffects!!.contains(StatusEffect.CurseOfMaledicto) }) {
            curseOfMaledicto
        } else if (this.mana == deathStrike.cost) {
            this.abilityList.first { it == deathStrike }
        } else {
            return this.abilityList.filter { it != curseOfMaledicto  }.filter { it != deathStrike }.filter { it != summonUndeadKnight }.random()
        }
    }


}