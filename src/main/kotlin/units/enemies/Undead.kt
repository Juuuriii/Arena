package units.enemies

import abilities.Ability
import abilities.infestedClaw
import enums.EnemyType

open class Undead(
    name: String,
    maxHp: Int,
    spellPwr: Int,
    attackPwr: Int,
    armor: Int,

    level: Int = 1,
    attacks: MutableList<Ability>,

    ) : Enemy(name, maxHp, spellPwr, attackPwr, armor, EnemyType.Undead, level) {

    init {
        this.abilityList = attacks
    }


    override fun takeHeal(amount: Int) {
        this.curHp -= amount
        if (this.curHp <= 0) {
            this.isDead = true
        }
    }
}