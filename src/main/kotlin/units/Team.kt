package units

import enums.PotionType
import item.Potion
import utils.devider
import java.io.File

class Team(

    var members: MutableList<PlayerChar>,
    var teamName: String = "",
) {

    var teamInventory = members.first().teamInventory

    var SAVE_PATH = "src/main/kotlin/save_files/$teamName.csv"
    var saveFile = File(SAVE_PATH)

    fun isTeamDead(): Boolean {

        return this.members.any { !it.isDead }

    }

    fun takeReward(reward: Int, exp: Int) {
        devider()
        println("You earned $reward Gold")
        this.members[0].teamInventory.gold += reward
        println()
        this.members.filter { !it.isDead }.forEach {
            it.gainExp(exp)
        }
    }

    fun printArenaPoints(): Int {
        return this.members[0].teamInventory.gold
    }

    fun printStatus() {
        var pad = 10
        println("Current Team Status:")

        println()
        this.members.forEach {
            println(
                " " + it.printLife() + "HP: ${it.maxHp}".padEnd(9) + "Attack Power: ${it.attackPwr}".padEnd(18) + "Spell Power: ${it.spellPwr}".padEnd(
                    18
                ) + "Armor: ${it.armor}"
            )
        }
        println()
        println(" Gold: " + this.printArenaPoints())
    }

    fun saveTeam() {
        saveFile.writeText("class,name,maxHp,spellPwr,attackPwr,armor,level,exp" + "\n")

        saveFile.appendText(this.teamInventory.saveGold()+"\n")

        this.members.first().teamInventory.inventory.forEach {
            saveFile.appendText((it as Potion).savePotion() + "\n")
        }
        this.members.forEach {
            saveFile.appendText(it.save() + "\n")
        }
    }

    fun loadTeam() {
        val saveFileLines: List<String> = saveFile.readLines()
        this.teamInventory.inventory.clear()
        var i = 0

        for (line in saveFileLines) {
            if (i == 0) {
                i++
                continue
            }
            var data = line.split("_")
            when (data[0]) {
                "Gold" -> {
                    this.teamInventory.gold = data[1].toInt()
                }

                "Potion" -> {
                    var potionType: PotionType
                    if (data[4] == "Mana") {
                        potionType = PotionType.Mana
                    } else {
                        potionType = PotionType.Health
                    }

                    this.teamInventory.inventory.add(Potion(data[1], data[2].toInt(), data[3].toInt(), potionType))
                }
                "Ranger" -> {
                    this.members[0].name = data[1]
                    this.members[0].maxHp = data[2].toInt()
                    this.members[0].spellPwr = data[3].toInt()
                    this.members[0].attackPwr = data[4].toInt()
                    this.members[0].armor = data[5].toInt()
                    this.members[0].level = data[6].toInt()
                    this.members[0].exp = data[7].toInt()
                }
                "Mage"->{
                    this.members[1].name = data[1]
                    this.members[1].maxHp = data[2].toInt()
                    this.members[1].spellPwr = data[3].toInt()
                    this.members[1].attackPwr = data[4].toInt()
                    this.members[1].armor = data[5].toInt()
                    this.members[1].level = data[6].toInt()
                    this.members[1].exp = data[7].toInt()
                }
                "Paladin" -> {
                    this.members[2].name = data[1]
                    this.members[2].maxHp = data[2].toInt()
                    this.members[2].spellPwr = data[3].toInt()
                    this.members[2].attackPwr = data[4].toInt()
                    this.members[2].armor = data[5].toInt()
                    this.members[2].level = data[6].toInt()
                    this.members[2].exp = data[7].toInt()
                }

            }


        }


    }


}