package com.omegajak.artisanapp

import android.content.Context
import android.util.Log
import com.github.salomonbrys.kotson.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.io.BufferedReader
import java.io.InputStreamReader

object DailyCreationManager {
    var dailyCreations = ArrayList<DailyCreation>()

    var onDailyCreationAdded: (() -> Unit)? = null
    var onDailyCreationRemoved: ((position: Int) -> Unit)? = null

    init {
        dailyCreations.add(DailyCreation(CreationDataManager.creationDataCollection[0], 10, 1))
    }

    fun numDistinctDailyCreations() : Int {
        return dailyCreations.size
    }

    fun save() {
        val str = gson.toJson(dailyCreations)
        Log.d(TAG, "Saved: $str")

        val context: Context = ArtisanApplication.getContext()
        val filename = "creations.json"
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(str.toByteArray())
        }
    }


    fun load() {
        val context: Context = ArtisanApplication.getContext()
        val filename = "creations.json"

        val inputStream = context.openFileInput(filename)
        val reader = BufferedReader(InputStreamReader(inputStream))
        //val text = reader.readText()
        //Log.d(TAG, text)

        dailyCreations = gson.fromJson(reader)
    }

    fun addCreation(creation : DailyCreation) {
        dailyCreations.add(creation)
        onDailyCreationAdded?.invoke()
        save()
    }

    fun removeCreation(creation: DailyCreation) {
        val index = dailyCreations.indexOf(creation)
        dailyCreations.removeAt(index)
        onDailyCreationRemoved?.invoke(index)
        save()
    }

    private val gson = GsonBuilder().registerTypeAdapter<TypeSpecificData> {
        serialize {
            val json: JsonObject = jsonObject(
                    it.src::creationType.name to it.src.creationType.toString()
            )

            when (it.src) {
                is ArcanismData -> {
                    json.addProperty((it.src as ArcanismData)::validSpells.name, it.context.serialize((it.src as ArcanismData).validSpells))
                }
            }

            json
        }

        deserialize {
            val jsonObj = it.json.asJsonObject
            var result: TypeSpecificData

            val creationType: TypeSpecificData.CreationType = TypeSpecificData.CreationType.valueOf(jsonObj.get(TypeSpecificData::creationType.name).asString)
            if (creationType == TypeSpecificData.CreationType.Arcanism || jsonObj.has(ArcanismData::validSpells.name)) {
                var spells: ArrayList<String> = ArrayList()
                if (jsonObj.has(ArcanismData::validSpells.name)) {
                    spells = it.context.deserialize(jsonObj.get(ArcanismData::validSpells.name))
                }

                result = ArcanismData(spells)
            } else {
                result = TypeSpecificData(creationType)
            }

            result
        }
    }.create()
}