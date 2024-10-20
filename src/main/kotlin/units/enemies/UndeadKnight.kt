package units.enemies

import abilities.Ability
import abilities.heavyStrike
import abilities.sweepingStrike
import abilities.swordStrike
import enums.EnemyType

class UndeadKnight(
    name: String,
    maxHp: Int,
    spellPwr: Int,
    attackPwr: Int,
    armor: Int,
    level: Int = 1


): Undead(name, maxHp, spellPwr, attackPwr, armor,level, mutableListOf() ) {


    init {
        this.abilityList.add(swordStrike)
        this.abilityList.add(sweepingStrike)
        this.abilityList.add(heavyStrike)
    }


    override fun pickAbility(): Ability {
        if(this.mana == heavyStrike.cost) {
            return heavyStrike
        } else {
            return abilityList.filter { it.cost == 0 }.random()
        }
    }

}