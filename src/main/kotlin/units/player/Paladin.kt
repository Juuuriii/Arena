package units.player

import abilities.*
import enums.EnemyType
import item.Inventory
import units.PlayerChar

class Paladin(
    name: String,
    maxHp: Int,
    spellPwr: Int,
    attackPwr: Int,
    armor: Int,
    inventory: Inventory,
    level: Int = 1
): PlayerChar(name, maxHp, spellPwr, attackPwr, armor, EnemyType.Humanoid, level, inventory) {

    init {
        this.abilityList.add(flashHeal)
        this.abilityList.add(smite)
        this.abilityList.add(crusaderStrike)
        this.abilityList.add(waveOfLight)
        this.abilityList.add(useItem)
    }


}