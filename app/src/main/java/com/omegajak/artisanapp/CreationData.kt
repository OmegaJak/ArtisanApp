package com.omegajak.artisanapp

import java.io.Serializable

class CreationData(val name: String,
                   val artistryPointCost: Int?,
                   val fullDescription: String,
                   val briefDescription: String,
                   val characteristic: String,
                   val tier: String,
                   val creationType: CreationData.CreationType) : Serializable {
    enum class CreationType {
        Alchemy, Animation, Arcanism, Artifice
    }
}