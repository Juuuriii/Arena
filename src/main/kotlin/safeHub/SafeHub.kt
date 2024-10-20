package safeHub

import load
import units.Team
import utils.devider
import kotlin.system.exitProcess

class SafeHub(var ownTeam: Team) {

    var theArena = Arena(this.ownTeam)
    var thePotionSHop = PotionShop(this.ownTeam)
    init {
        safeHubOverview()
    }
    fun safeHubOverview() {
        devider()
        println("Welcome to the Safe Hub Gladiators")
        devider()

        println("""
               What would you like to do?
               
                1) To the Arena!
                2) Potion Shop
                
                3) Save
                4) Load
                
                5) Exit
                
        """.trimIndent())

        this.ownTeam.printStatus()
        println()
        println("Enter the number of the action you would like to take:")
        when(readln()) {

            "1" -> {
                theArena.startArena()
                this.safeHubOverview()
            }
            "2" -> {
                devider()
                thePotionSHop.runPotionShop()
                this.safeHubOverview()
            }
            "3" -> {
                this.ownTeam.saveTeam()
                devider()
                println("Your Team has been saved")
                this.safeHubOverview()
            }

            "4" -> {
                load()
            }

            "5" -> {
                exitProcess(0)
            }
            else -> {
                devider()
                println("Not a valid input. Please try again")
                this.safeHubOverview()
            }

        }

    }

}