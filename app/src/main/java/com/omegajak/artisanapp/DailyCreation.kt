package com.omegajak.artisanapp

class DailyCreation(var creationData: CreationData, numMade: Int, var artistryPointCost: Int = creationData.artistryPointCost!!, currentSpell: String? = null) {
    var numMade: Int = numMade
        private set(value) {
            field = value
            onNumMadeChanged?.invoke()
            DailyCreationManager.save()
        }

    @Transient var onNumMadeChanged: (() -> Unit)? = null

    var currentSpell: String? = currentSpell
        set(value) {
            field = value
            DailyCreationManager.save()
        }

    fun incrementNumMade() {
        numMade++
    }

    fun decrementNumMade() {
        if (numMade > 0) numMade--
        if (numMade <= 0 && !DailyCreationManager.isChoosing) DailyCreationManager.removeCreation(this)
    }

    fun resetNumMade() {
        numMade = 0
    }

    fun getTotalCost() : Int {
        return numMade * artistryPointCost
    }
}