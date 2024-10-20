package units.player

import abilities.*
import enums.EnemyType
import item.Inventory
import units.PlayerChar

class Ranger(
    name: String,
    maxHp: Int,
    spellPwr: Int,
    attackPwr: Int,
    armor: Int,
    inventory: Inventory,
    level: Int = 1
) : PlayerChar(name, maxHp, spellPwr, attackPwr, armor, EnemyType.Humanoid, level, inventory) {

    init {
        this.abilityList.add(quickShot)
        this.abilityList.add(multiShot)
        this.abilityList.add(poisonArrow)
        this.abilityList.add(aimedShot)
        this.abilityList.add(useItem)
    }

}