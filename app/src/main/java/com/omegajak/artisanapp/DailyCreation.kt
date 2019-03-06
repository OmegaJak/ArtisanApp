package com.omegajak.artisanapp

class DailyCreation(var creationData: CreationData, var numMade: Int, var artistryPointCost: Int = creationData.artistryPointCost!!) {
    var totalCost = numMade * artistryPointCost
}