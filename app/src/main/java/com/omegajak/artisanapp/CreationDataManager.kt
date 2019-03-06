package com.omegajak.artisanapp

import com.google.gson.Gson

object CreationDataManager {
    val creationDataCollection = ArrayList<CreationData>()

    init {
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
                creationType = CreationData.CreationType.Artifice))
    }
}