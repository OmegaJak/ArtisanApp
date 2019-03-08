package com.omegajak.artisanapp

import java.io.Serializable

open class TypeSpecificData(val creationType: CreationType) : Serializable {
    enum class CreationType {
        Alchemy, Animation, Arcanism, Artifice
    }
}

class ArcanismData(var validSpells : ArrayList<String>) : TypeSpecificData(CreationType.Arcanism)
