package com.omegajak.artisanapp

import com.google.gson.Gson

object CreationDataManager {
    val creationDataCollection = ArrayList<CreationData>()

    init {
        for (i in 0..19) {
            if (i == 0) {
                creationDataCollection.add(CreationData(
                        name = "Level 1 Rune Stone Part 1",
                        artistryPointCost = 4,
                        fullDescription = "",
                        briefDescription = "",
                        characteristic = "",
                        tier = "1st",
                        typeSpecificData = ArcanismData(arrayListOf("Absorb Elements", "Alarm (ritual)", "Catapult"))
                ))
            } else if (i == 1) {
                creationDataCollection.add(CreationData(
                        name = "Level 1 Rune Stone Part 2",
                        artistryPointCost = 4,
                        fullDescription = "",
                        briefDescription = "",
                        characteristic = "",
                        tier = "1st",
                        typeSpecificData = ArcanismData(arrayListOf("Cause Fear", "Charm Person", "Color Spray"))
                ))
            } else if (i > 1) {
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
            }
        }
    }
}