import enums.PotionType
import item.Inventory
import item.Potion
import safeHub.Battle
import safeHub.dragonLevel5
import safeHub.orcLevel5
import safeHub.undeadLevel5
import units.Team
import units.enemies.BossMaledicto
import units.player.Mage
import units.player.Paladin
import units.player.Ranger

fun main() {

    var inventory = Inventory()
    inventory.inventory.add(Potion("Large Health Potion", 90, 10, PotionType.Health))
    inventory.inventory.add(Potion("Large Health Potion", 90, 10, PotionType.Health))
    inventory.inventory.add(Potion("Large Mana Potion", 100, 6, PotionType.Mana))

    var ranger = Ranger("Gordon", 20, 1, 6, 3, inventory,10)
    var mage = Mage("Brian", 17,5,1, 3, inventory, 10)
    var paladin = Paladin("Laurenz", 22,2,3,3, inventory, 10)

    var ownTeam = Team(
        mutableListOf(ranger,mage,paladin)
    )
    var enemyTeam = Team(
        mutableListOf(BossMaledicto("Maledicto", 50, 4, 6, 3, 15, ownTeam))
    )
    Battle(ownTeam,enemyTeam).fight()
}
