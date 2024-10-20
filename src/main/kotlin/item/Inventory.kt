package item

import enums.PotionType
import units.PlayerChar
import utils.devider



class Inventory(


    var gold: Int = 200

    ){

    var inventory: MutableList<Item> = mutableListOf()





    fun buyPotion(potion: Potion){
        if(this.gold < potion.goldValue){
            devider()
            println("You do not have enough Gold")
        } else {
            this.inventory.add(potion)
            this.gold -= potion.goldValue
            devider()
            println("You bought ${potion.name} for ${potion.goldValue} Gold.")
        }
    }



    fun pickUsables(): Item{
devider()
        this.printInventoryPotion()
        println()
        println("Enter the number of the item you want to use:")
       return try {
           var input = readln().toInt()
           var item = this.inventory.filterIsInstance<Potion>()[input-1]
           this.inventory.remove(item)
           item
        } catch (e :Exception){
            devider()
            println("Not a valid input. Please try again.")
           pickUsables()
        }
    }
    fun printInventoryPotion(withIndex: Boolean = true){
        this.inventory.sortByDescending { it.name }
        println("Your Inventory:")
        println()
        if ( withIndex) {
        this.inventory.filterIsInstance<Potion>().forEachIndexed { index, item ->

            println("${index + 1}) " + item.name +" - "+ item.description)
        }} else {
            this.inventory.filterIsInstance<Potion>().forEachIndexed { index, item ->

                println(" " + item.name +" - "+ item.description)
            }
        }
    }
    fun saveGold(): String {
        return "Gold_${this.gold}"
    }
}