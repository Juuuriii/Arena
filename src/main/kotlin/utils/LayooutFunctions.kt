package utils

import units.Team
import abilities.Ability
import abilities.quickShot
import enums.StatusEffect


fun displayHpBar(curHp: Int, maxHp: Int): String {
    var distance = 16
    if (maxHp >= 31) {
        distance = maxHp + 6
    } else if (maxHp >= 30) {
        distance = 36
    } else if (maxHp >= 20) {
        distance = 26
    } else if (maxHp >= 10) {
        distance = 16
    }

    var curHpBar = ""
    var missingHp = maxHp - curHp
    for (i in 1..curHp) {
        curHpBar = curHpBar.plus("|".color(RED).color(BOLD))
    }
    return if (missingHp != 0) {
        var missingHpBar = ""
        for (i in 1..missingHp) {
            missingHpBar = missingHpBar.plus("|".color(GREY))
        }
        var healthBar = "HP[$curHpBar$missingHpBar]"
        healthBar.padEnd(healthBar.length - maxHp + distance)
    } else {
        var healthBar = "HP[$curHpBar]"
        healthBar.padEnd(healthBar.length - maxHp + distance)
    }

}

fun displayManaBar(mana: Int): String {
    var manaBar = ""
    var distance = 6
    if (mana > 8) {distance = 15}
    for (i in 1..mana) {
        manaBar = manaBar.plus("*".color(BLUE).color(BOLD))
    }
    var displayManaBar = "Mana[$manaBar]"


    return displayManaBar.padEnd(displayManaBar.length - mana + distance)
}

fun displayDead(isDead: Boolean): String {

    return if (isDead) {
        "DEFEATED".color(BRIGHTWHITE).color(BOLD)
    } else {
        ""
    }
}

fun displayManaCost(ability: Ability): String {

    var manaCost = ""
    for (i in 1..ability.cost) {
        manaCost = manaCost.plus("*".color(BLUE).color(BOLD))
    }
    var displayManaCost = "Mana Cost: $manaCost"

    var manaGen = ""

    for (i in 1..ability.generate) {
        manaGen = manaGen.plus("*".color(BLUE).color(BOLD))
    }
    var displayManaGen = "Mana Gain: $manaGen"



    return if (ability.generate == 0) {

        displayManaCost.padEnd(displayManaCost.length - ability.cost + 8)
    } else {
        displayManaGen.padEnd(displayManaGen.length - ability.generate + 8)
    }
}

fun pressEnterToContinue() {
    println(" Press Enter to Continue ".color(BOXED))
    println()
    var input = readln()
}

fun printTeam(team: Team) {
    println()
    team.members.forEachIndexed { index, playerChar ->
        println("${index + 1}) ${playerChar.printLife()}")
    }
    println()
}

fun displayAmountOfTargets(ability: Ability): String {

    var returnString: String = ""

    when (ability.amountTargets) {

        1 -> returnString = "Targets: One"

        3 -> returnString = "Targets: Team"

        6 -> returnString = "Targets: All"
    }


    return returnString.padEnd(18)

}

fun displayStatusEffects(statusEffect: MutableList<StatusEffect>?): String {

    var display: String = ""
    if (statusEffect!!.isNotEmpty()) {
        statusEffect.forEach {
            display = display.plus("${it.statusName} ")
        }
        display = "Status Effects: $display"
    }
    return display
}