import item.Inventory
import safeHub.SafeHub
import units.Team
import units.player.*
import utils.devider
import java.io.File
import kotlin.system.exitProcess

fun main() {


    welcomeScreen()

}
var TEAM_NAME_FILE = "src/main/kotlin/save_files/Teams.cvs"
fun welcomeScreen() {
devider()
    println(
        """
        Welcome to the Arena!
        
         1) New Game
         2) Load Game
        
         3) Exit
       
    """.trimIndent()
    )
    println("Enter the number of your Action:")
    newOrLoad(readln())

}
fun newOrLoad(input: String) {
    when(input){

        "1" -> {SafeHub(newGame())}
        "2" -> {SafeHub(load())}
        "3" -> {
            exitProcess(0)}
        else -> {
            devider()
            println("Not a valid input. Please try again")
            welcomeScreen()
        }
    }

}

fun newGame(): Team {
    devider()
    println("Enter a name for your Team:")
    val teamName = readln()
    if (File("src/main/kotlin/save_files/$teamName.csv").isRooted) {
        devider()
        println("Team name already exists. Please pick a different Team name.")
        newGame()
    }
    if (teamName.contains("\n")) {
        println("Team name is not allowed to contain \"\\n\"")
        newGame()
    }
    File(TEAM_NAME_FILE).appendText(teamName + "\n")
    devider()
    println("Enter the Name of your Ranger:")
    val rangerName = readln()
    devider()
    println("Enter the Name of your Mage:")
    val mageName = readln()
    devider()
    println("Enter the Name of your Paladin:")
    val paladinName = readln()


    val inventory = Inventory()

    val ownTeam = Team(
        mutableListOf(
            Ranger(rangerName, 8, 1, 2, 1, inventory),
            Mage(mageName, 7, 2, 1, 1, inventory),
            Paladin(paladinName, 10, 1, 1, 2, inventory)
        ),
        teamName
    )
    ownTeam.saveTeam()

    return ownTeam
}
fun load(): Team {

    val data = File(TEAM_NAME_FILE).readLines()
    if(data.isEmpty()) {
        devider()
        println("No save files there yet. Start a new game")
        welcomeScreen()
    }
    devider()
    println("Which Team would you like to load?")

    data.forEachIndexed { index, team ->
        println()
        println(" ${index + 1}) $team")
    }
    println()
    println("Enter the number of the team you want to load:")
    return try {
        val teamName = data[readln().toInt() - 1]
        val inventory = Inventory()
        val team = Team(
            mutableListOf(
                Ranger("rangerName", 14, 1, 2, 2, inventory),
                Mage("rangerName", 12, 3, 1, 1, inventory),
                Paladin("PaladinName", 16, 1, 1, 3, inventory)
            ),
            teamName
        )
        team.loadTeam()
        team
    } catch (e: Exception) {
        devider()
        println("Not a valid input. Please try again")
        load()
    }

}

