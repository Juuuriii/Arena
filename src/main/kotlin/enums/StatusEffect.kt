package enums

import utils.*


enum class StatusEffect( var statusName: String) {
    Poisoned("Poison".color(BRIGHTGREEN)),
    Frozen("Frozen".color(BRIGHTBLUE)),
    Burning("Burning"),
    CurseOfMaledicto("Curse of Maledicto".color(PURPLE)),
    BlessingOfMaledicto("Blessing of Maledicto".color(RED))}