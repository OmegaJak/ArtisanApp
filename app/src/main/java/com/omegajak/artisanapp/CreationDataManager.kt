package com.omegajak.artisanapp

import com.google.gson.Gson

object CreationDataManager {
    val creationDataCollection = ArrayList<CreationData>()

    init {
        creationDataCollection.add(CreationData(
                name = "Level 1 Rune Stone Part 1",
                artistryPointCost = 2,
                fullDescription = "",
                briefDescription = "",
                characteristic = "",
                tier = "1st",
                typeSpecificData = ArcanismData(arrayListOf("Absorb Elements", "Alarm (ritual)", "Catapult", "Cause Fear", "Charm Person", "Color Spray",
                        "Chromatic Orb", "Detect Magic (ritual)", "Disguise Self", "Feather Fall (Monocle)", "Identify (Monocle)", "Magic Missile", "Shield", "Tenser's Floating Disk (ritual)"))
        ))

        creationDataCollection.add(CreationData(
                name = "Level 2 Rune Stone",
                artistryPointCost = 3,
                fullDescription = "",
                briefDescription = "",
                characteristic = "",
                tier = "1st",
                typeSpecificData = ArcanismData(arrayListOf("Arcane Lock", "Blindness/Deafness", "Gentle Respose (ritual)", "Heat Metal", "Hold Person", "Invisibility", "Mind Spike",
                        "Maximilian's Earthen Grasp", "Phantasmal Force", "Silence (ritual)", "Skywrite (ritual)"))
        ))

        creationDataCollection.add(CreationData(
                name = "Level 3 Rune Stone",
                artistryPointCost = 5,
                fullDescription = "",
                briefDescription = "",
                characteristic = "",
                tier = "2nd",
                typeSpecificData = ArcanismData(arrayListOf("Blink", "Call Lightning", "Clairvoyance", "Counterspell (Monocle)", "Enemies Abound", "Fear", "Fly (Monocle)",
                        "Glyph of Warding", "Leomund's Tiny Hut (ritual)", "Meld Into Stone (ritual)", "Melf's Minute Meteors", "Sending", "Wall of Sand", "Wall of Water"))
        ))

        creationDataCollection.add(CreationData(
                name = "Kinetic Charge Gauntlet",
                artistryPointCost = null,
                fullDescription = "The gauntlet has charges equal to the amount of artistry points used in its creation divided by four. As a bonus action," +
                        "the wearer can expend a charge and activate the gauntlet, which remains activated for 1 minute. While the gauntlet is activated," +
                        "the wearer deals an extra 1d6 force damage to any target the wearer hits with a weapon attack. A creature can only benefit from one" +
                        "instance of this effect at a time.",
                briefDescription = "While activated, the wearer deals an extra 1d6 force damage to any target the wearer hits with a weapon attack.",
                characteristic = "Equipment: Gloves and gauntlets",
                tier = "1st",
                typeSpecificData = TypeSpecificData(TypeSpecificData.CreationType.Artifice)))

        creationDataCollection.add(CreationData(
                name = "Potion of Health",
                artistryPointCost = 4,
                fullDescription = "When administered to a creature, that creature regains 2d6 + your Intelligence modifier hit points",
                briefDescription = "When administered to a creature, that creature regains 2d6 + your Intelligence modifier hit points",
                characteristic = "",
                tier = "1st",
                typeSpecificData = TypeSpecificData(TypeSpecificData.CreationType.Alchemy)
        ))

        creationDataCollection.add(CreationData(
                name = "Monocle of the Weave",
                artistryPointCost = 6,
                fullDescription = "This monocle can be used to learn and transcribe the schematics for spells not on the existing rune stone lists.\n" +
                        "\n" +
                        "A spell in written form (such as in a spellbook or on a scroll) can be transcribed into an artisan's rune schematic book through careful study. " +
                        "For each level of the spell, this process takes 2 hours and costs 10gp.\n" +
                        "\n" +
                        "Once a spell learned this way is transcribed into a schematic book, it can once again be deciphered using the monocle, in order to create a rune stone " +
                        "inscribed with that spell. Doing so takes 1 minute and the appropriate cost in artistry points for the corresponding level of rune stone.",
                briefDescription = "This monocle can be used to learn and transcribe the schematics for spells not on the existing rune stone lists.",
                characteristic = "",
                tier = "1st",
                typeSpecificData = TypeSpecificData(TypeSpecificData.CreationType.Artifice)
        ))

        creationDataCollection.add(CreationData(
                name = "Condensed Alchemist's Fire",
                artistryPointCost = 2,
                fullDescription = "Condensed alchemist's fire can be used to blanket an area in flames. Each creature in the affected area must make a DEX saving throw." +
                        "A creature takes 2d6 fire damage on a failed save, or half as much damage on a successful one.\n" +
                        "The fire ignites any flammable objects in the area that aren't being worn or carried.\n" +
                        "The creation save DC = 8 + PROF + INT",
                briefDescription = "10-foot radius 2d6 fire after failed save, half damage on successful save",
                characteristic = "10-foot radius sphere",
                tier = "1st",
                typeSpecificData = TypeSpecificData(TypeSpecificData.CreationType.Alchemy)
        ))

        creationDataCollection.add(CreationData(
                name = "Thrust Mine",
                artistryPointCost = 4,
                fullDescription = "This Tiny device can be safely set down at a position as an action, and then activates whenever a creature approaches to within 10 feet of the thrust mine.\n" +
                        "There are three different uses for thrust mines, and each have different compatible equipment.\n" +
                        "Concoction Launcher: A thrust mine can be equipped to an alchemy creation with an area of effect. In this case, the mine automatically launches the creation at the " +
                        "creature that triggers the mine. The thrust mine is then expended.\n" +
                        "Arcane Trap: A thrust mine can be equipped to an arcanism creation that stores a spell that causes harm and that targets only one create and has a range of at least 10 " +
                        "feet or that has an area of effect. When the mine is triggered, the spell in the arcanism creation is cast. If the spell has a target, it targets the creature that triggered " +
                        "the mine. If the spell affects an area, the area is centered on the triggering creature. If the spell requires concentration, it lasts until the end of its full duration," +
                        " at which point the thrust mine is expended.\n" +
                        "Force Snag: If equipped to an arcane focus, the thrust mine fires a single magic missile dart at any creature that activates it. The dart hits automatically and deals 1d4 + 1" +
                        " force damage. The thrust mine lasts until it is dealt damage that is not psychic or poison, at which point the mine is expended and ceases to function.",
                briefDescription = "Either a Concoction Launcher, an Arcane Trap, or a Force Snag.",
                characteristic = "",
                tier = "2nd",
                typeSpecificData = TypeSpecificData(TypeSpecificData.CreationType.Artifice)
        ))
    }
}
