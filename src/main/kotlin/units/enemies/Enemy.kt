package units.enemies

import units.Team
import abilities.Ability
import enums.AbilityType
import enums.EnemyType
import item.Item
import units.PlayerChar

open class Enemy(
    name: String,
    maxHp: Int,
    spellPwr: Int,
    attackPwr: Int,
    armor: Int,
    type: EnemyType,
    level: Int = 1,

    ) : PlayerChar(name, maxHp, spellPwr, attackPwr, armor, type, level) {


    override fun pickAbility(): Ability {
        return if (this.mana >= this.abilityList.maxBy { it.cost }.cost) {
            this.abilityList.maxBy { it.cost }
        } else if (this.curHp < this.maxHp && this.abilityList.any { it.type == AbilityType.Heal }) {
            this.abilityList.filter { it.type == AbilityType.Heal }.random()
        } else {
            this.abilityList.filter { it.cost == 0 }.random()
        }



    }

    override fun pickTarget(ownTeam: Team, enemyTeam: Team, pickedAbility: Ability): MutableList<PlayerChar> {
        var targets: MutableList<PlayerChar> = mutableListOf()

        when (pickedAbility.type) {

            AbilityType.Heal -> {
                if (pickedAbility.amountTargets == 1) {

                    targets.add(enemyTeam.members.maxBy { it.maxHp - it.curHp })
                } else if (pickedAbility.amountTargets == 3) {
                    enemyTeam.members.filter { !it.isDead }.forEach {
                        targets.add(it)
                    }
                } else {
                    targets.addAll(ownTeam.members.filter { !it.isDead })
                    targets.addAll(enemyTeam.members.filter { !it.isDead })
                }
            }

            AbilityType.Summon -> {
                return enemyTeam.members
            }

            else -> {
                if (pickedAbility.amountTargets == 1) {
                    targets.add(ownTeam.members.filter { !it.isDead }.random())
                } else if (pickedAbility.amountTargets == 3) {
                    ownTeam.members.filter { !it.isDead }.forEach { targets.add(it) }
                } else {
                    targets.addAll(ownTeam.members.filter { !it.isDead })
                    targets.addAll(enemyTeam.members.filter { !it.isDead })
                }
            }
        }
        return targets
    }
}