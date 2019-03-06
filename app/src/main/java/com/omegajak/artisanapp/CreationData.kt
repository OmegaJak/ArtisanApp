package com.omegajak.artisanapp

import java.io.Serializable

class CreationData(val name: String,
                   val artistryPointCost: Int?,
                   val fullDescription: String,
                   val briefDescription: String,
                   val characteristic: String,
                   val tier: String,
                   val typeSpecificData : TypeSpecificData) : Serializable {

}