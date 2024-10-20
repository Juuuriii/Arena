package units.enemies

import abilities.Ability
import abilities.fireBreath
import enums.EnemyType

class Dragon (
    name: String,
    maxHp: Int,
    spellPwr: Int,
    attackPwr: Int,
    armor: Int,
    level: Int = 1,
    attacks: MutableList<Ability>

    ): Enemy(name, maxHp, spellPwr, attackPwr, armor, EnemyType.Dragon, level) {



init {
    this.abilityList = attacks
}



}