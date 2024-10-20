package safeHub


import abilities.*
import units.enemies.*



var orcLevel1 = Humanoid(
    "Orc",
    12,
    1,
    1,
    2,
    1,
    mutableListOf(clubSmash)
)


var zombieLevel1 = Undead(
    "Zombie",
    8,
    1,
    2,
    1,
    1,
    mutableListOf(infestedClaw))

var dragonLevel1 = Dragon(
    "Small Dragon",
    8,
    2,
    1,
    1,
    1,
    mutableListOf(fireBreath)
)

var orcLevel5 = Humanoid(
    "Orc Shaman",
    12,
    3,
    1,
    2,
    5,
    mutableListOf(lightningBold, lesserHeal, chainLightning)
)

var dragonLevel5 = Dragon(
    "Dragon Warrior",
    15,
    1,
    3,
    2,
    5,
    mutableListOf(tailSwipe, iceBreath, furiousBite)
)
var undeadLevel5 = Undead(
    "Abomination",
    15,
    1,
    1,
    3,
    5,
    mutableListOf(foulStench, cleave, mutilate)
)