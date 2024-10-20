package safeHub

import enums.PotionType
import item.Potion
import units.Team
import utils.devider

class PotionShop(private var ownTeam: Team) {

    fun runPotionShop() {
        this.welcomeMessage()

        this.printShopInventory()

        this.choosePotionToBuy(readln())

        this.buyMore(readln())
    }
    private fun welcomeMessage(){
        devider()
        println("Welcome to my Shop Gladiators. Looking for some goods to make the next battles easier?")
        devider()
    }
    private fun printShopInventory(){
        println("""
            Potion Shop:                                  
            
             1) Health Potion            Effect: Gain 5  HP          Cost: 50 Gold
             2) Large Health Potion      Effect: Gain 10 HP          Cost: 90 Gold
             3) Mana Potion              Effect: Gain 3  Mana        Cost: 60 Gold
             4) Large Mana Potion        Effect: Gain 6  Mana        Cost: 100 Gold   
                
             5) Back to Safe Hub
                
            Your Gold: ${this.ownTeam.printArenaPoints()} Gold
            
        """.trimIndent())
        this.ownTeam.members.first().teamInventory.printInventoryPotion(false)
        println()
        println("Enter the number of the Potion you want to buy:")
        choosePotionToBuy(readln())
    }
    private fun choosePotionToBuy(input: String) {
        when(input) {

            "1" -> {
                this.ownTeam.members[0].teamInventory.buyPotion(Potion("Health Potion",50, 5, PotionType.Health))
            }
            "2" -> {
                this.ownTeam.members[0].teamInventory.buyPotion(Potion("Large Health Potion",90, 10, PotionType.Health))
            }
            "3" -> {
                this.ownTeam.members[0].teamInventory.buyPotion(Potion("Mana Potion",60, 3, PotionType.Mana))
            }
            "4" -> {
                this.ownTeam.members[0].teamInventory.buyPotion(Potion("Large Mana Potion",100, 6, PotionType.Mana))
            }
            "5" -> {
                devider()
                println("Come back any time!")
                SafeHub(ownTeam).safeHubOverview()}
            else -> {
                devider()
                println("Not a valid input. Please try again")
                this.printShopInventory()
            }
        }
        devider()
        println("""
            Would you like to buy another Potion?
            
             1) Yes
             2) No
                
        """.trimIndent())
        buyMore(readln())
    }
    private fun buyMore(input: String){

        when(input) {
            "1"-> {this.printShopInventory()}

            "2"-> {
                SafeHub(this.ownTeam)
            }

            else -> {
                println()
                println("That is not a valid option. Please enter 1 or 2.")
                this.buyMore(readln())
            }
        }


    }

}