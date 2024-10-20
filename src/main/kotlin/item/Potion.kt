package item

import enums.PotionType
import units.PlayerChar
import utils.devider

class Potion(
    name: String,
    goldValue: Int,
    var gain: Int,
    var type: PotionType

): Item(name , goldValue, description = "Gain $gain ${type.name}") {


    override fun use(playerChar: PlayerChar){
        println()
        println("${playerChar.name} gains $gain ${this.type.name} from drinking ${this.name}")
        when(this.type){
            PotionType.Health -> {
                playerChar.curHp += this.gain
                playerChar.usingItem.clear()
            }

            PotionType.Mana -> {
                playerChar.mana += this.gain
                playerChar.usingItem.clear()
            }
        }

    }
    fun savePotion(): String {


        return "${this::class.simpleName}_${this.name}_${this.goldValue}_${this.gain}_${this.type.name}"


    }
}