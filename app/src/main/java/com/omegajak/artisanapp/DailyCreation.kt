package com.omegajak.artisanapp

import kotlin.properties.Delegates

class DailyCreation(var creationData: CreationData, numMade: Int, var artistryPointCost: Int = creationData.artistryPointCost!!) {
    var numMade : Int = numMade
        private set(value) {
            field = value
            onNumMadeChanged?.invoke()
            DailyCreationManager.save()
        }


    @Transient var onNumMadeChanged: (() -> Unit)? = null

    fun incrementNumMade() {
        numMade++
    }

    fun decrementNumMade() {
        if (numMade > 0) numMade--
        if (numMade == 0) DailyCreationManager.removeCreation(this)
    }

    fun getTotalCost() : Int {
        return numMade * artistryPointCost
    }
}