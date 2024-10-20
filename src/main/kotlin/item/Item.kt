package item

import units.PlayerChar


open class Item(
    var name: String,
    var goldValue: Int,
    var description: String
) {


    open fun use(playerChar: PlayerChar){}

}