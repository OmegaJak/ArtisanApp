package com.omegajak.artisanapp

open class TypeSpecificData(val creationType: CreationType) {
    enum class CreationType {
        Alchemy, Animation, Arcanism, Artifice
    }
}

class ArcanismData(var validSpells : ArrayList<String>) : TypeSpecificData(CreationType.Arcanism)
