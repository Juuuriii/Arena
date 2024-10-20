package abilities

import enums.AbilityType
import enums.StatusEffect

//TODO Balance Spells

//Use Potion----------------------------------------------------------------------------------------------------------------

var useItem = Ability(
    "Use Item",
    0,
    AbilityType.Item,
    0,
    0,
    null,
    ""
)

//Ranger----------------------------------------------------------------------------------------------------------------

var multiShot = Ability(
    "Multi Shot",
    1,
    AbilityType.Attack,
    0,
    1,
    null,
    "Shoots the whole Enemy Team with arrows",
    3
)
var quickShot = Ability(
    "Quick Shot",
    3,
    AbilityType.Attack,
    0,
    1,
    null,
    "Shoots a swift arrow at your target"
)
var poisonArrow = Ability(
    "Poison Arrow",
    2,
    AbilityType.Attack,
    2,
    0,
    StatusEffect.Poisoned,
    "Shoots a poisoned arrow at your target"
)

var aimedShot = Ability(
    "Aimed Shot",
    5,
    AbilityType.Attack,
    2,
    0,
    null,
    "Shoots a well aimed arrow at your target"
)

//Paladin----------------------------------------------------------------------------------------------------------------

var flashHeal = Ability(
    "Flash Heal",
    3,
    AbilityType.Heal,
    0,
    1,
    null,
    "Heals your target with a flash of light"
)

var smite = Ability(
    "Smite",
    1,
    AbilityType.Spell,
    0,
    1,
    null,
    "Smites your target with holy might"
)
var crusaderStrike = Ability(
    "Holy Strike",
    6,
    AbilityType.Attack,
    2,
    0,
    null,
    "Strikes your target with holy might",
    1
)
var waveOfLight = Ability(
    "Wave of Light",
    5,
    AbilityType.Heal,
    2,
    0,
    null,
    "Heals everybody in a chosen team",
    3
)

//Mage----------------------------------------------------------------------------------------------------------------

var staff = Ability(
    "Fire Burst",
    1,
    AbilityType.Spell,
    0,
    1,
    null,
    "Sends a burst of fire towards your target"
)

var arcaneMissile = Ability(
    "Arcane Missile",
    1,
    AbilityType.Spell,
    0,
    1,
    null,
    "Shoots a homing arcane missile at your target"
)

var fireBall = Ability(
    "Fireball",
    4,
    AbilityType.Spell,
    2,
    0,
    null,
    "Hurls a fireball at your target"
)

var frostBolt = Ability(
    "Frost Bolt",
    2,
    AbilityType.Spell,
    2,
    0,
    StatusEffect.Frozen,
    "Casts a Frost Bolt towards your target, freezing it"
)

// Enemy Abilities

// Undead----------------------------------------------------------------------------------------------------------------

var infestedClaw = Ability(
    "Infested Claw",
    2,
    AbilityType.Attack,
    0,
    0,
    null,
    ""
)

// Humanoid----------------------------------------------------------------------------------------------------------------

var clubSmash = Ability(
    "Club Smash",
    2,
    AbilityType.Attack,
    0,
    1,
    null,
    "Hits your target with a club"

)

// Dragon----------------------------------------------------------------------------------------------------------------

var fireBreath = Ability(
    "Fire Breath",
    2,
    AbilityType.Spell,
    0,
    0,
    null
)

// Boss----------------------------------------------------------------------------------------------------------------

var curseOfMaledicto = Ability(

    "Curse of Maledicto",
    2,
    AbilityType.Spell,
    0,
    1,
    StatusEffect.CurseOfMaledicto

)

var miasma = Ability(
    "Miasma",
    2,
    AbilityType.Spell,
    0,
    1,
    null,
    "",
    3
)

var unholyStrike = Ability(
    "Unholy Sword Strike",
    6,
    AbilityType.Attack,
    0,
    1,
    null
)

var frostBoltBoss = Ability(
    "Frost Bolt",
    3,
    AbilityType.Spell,
    0,
    1,
    StatusEffect.Frozen
)

var deathStrike = Ability(
    "Death Strike",
    12,
    AbilityType.Attack,
    3,
    0,
    null
)
var summonUndeadKnight = Ability(
    "Summon Undead Knight",
    0,
    AbilityType.Summon,
    0,
    0,
    null
)

// Undead Knight Abilities----------------------------------------------------------------------------------------------------------------

var swordStrike = Ability(
    "Sword Strike",
    5,
    AbilityType.Attack,
    0,
    1,
    null
)

var sweepingStrike = Ability(
    "Sweeping Strike",
    3,
    AbilityType.Attack,
    0,
    1,
    null,
    "",
    3
)

var heavyStrike = Ability(
    "Heavy Strike",
    10,
    AbilityType.Attack,
    3,
    0,
    null
)

// Orc Shaman-----------------------------------------------------------------------------------------------------------

var lesserHeal = Ability(
    "Lesser Heal",
    2,
    AbilityType.Heal,
    0,
    1,
    null
)
var lightningBold = Ability(
    "Lightning Bolt",
    2,
    AbilityType.Spell,
    0,
    1,
    null
)
var chainLightning = Ability(
    "Chain Lightning",
    2,
    AbilityType.Spell,
    2,
    0,
    null,
    "",
    3
)

// Dragon Warrior-------------------------------------------------------------------------------------------------------

var tailSwipe = Ability(
    "Tail Swipe",
    1,
    AbilityType.Attack,
    0,
    1,
    null,
    "",
    3
)

var iceBreath = Ability(
    "Ice Breath",
    1,
    AbilityType.Spell,
    0,
    1,
    StatusEffect.Frozen,
)

var furiousBite = Ability(
    "Furious Bite",
    6,
    AbilityType.Attack,
    2,
    0,
    null
)

// Abomination----------------------------------------------------------------------------------------------------------

var foulStench = Ability(
    "Foul Stench",
    1,
    AbilityType.Spell,
    0,
    1,
    StatusEffect.Poisoned,
    "",
    3
)

var cleave = Ability(
    "Cleave",
    1,
    AbilityType.Attack,
    0,
    1,
    null,
    "",
    3
)

var mutilate = Ability(
    "Mutilate",
    50,
    AbilityType.Attack,
    5,
    0,
    null,
)