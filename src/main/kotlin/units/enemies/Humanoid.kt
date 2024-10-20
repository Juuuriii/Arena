package units.enemies

import abilities.Ability
import abilities.clubSmash
import enums.EnemyType

class Humanoid(
    name: String,
    maxHp: Int,
    spellPwr: Int,
    attackPwr: Int,
    armor: Int,
    level: Int = 1,
    attacks: MutableList<Ability>

): Enemy(name, maxHp, spellPwr, attackPwr, armor, EnemyType.Humanoid, level) {

init {
    this.abilityList = attacks
}


}