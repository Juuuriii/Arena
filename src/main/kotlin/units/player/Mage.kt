package units.player

import abilities.*
import enums.EnemyType
import item.Inventory
import units.PlayerChar

class Mage(
    name: String,
    maxHp: Int,
    spellPwr: Int,
    attackPwr: Int,
    armor: Int,
    inventory: Inventory,
    level: Int = 1
): PlayerChar(name, maxHp, spellPwr, attackPwr, armor, EnemyType.Humanoid, level, inventory) {


    init {
        this.abilityList.add(staff)
        this.abilityList.add(arcaneMissile)
        this.abilityList.add(fireBall)
        this.abilityList.add(frostBolt)
        this.abilityList.add(useItem)
    }


}