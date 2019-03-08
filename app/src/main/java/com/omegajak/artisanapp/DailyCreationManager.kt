package com.omegajak.artisanapp

import android.content.Context
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

object DailyCreationManager {
    var dailyCreations = ArrayList<DailyCreation>()
    val dailyCreationsType = object: TypeToken<ArrayList<DailyCreation>>() {}.type
    val typeSpecificDataType = object: TypeToken<TypeSpecificData>() {}.type

    var onDailyCreationAdded: (() -> Unit)? = null
    var onDailyCreationRemoved: ((position: Int) -> Unit)? = null

    init {
        dailyCreations.add(DailyCreation(CreationDataManager.creationDataCollection[0], 10, 1))
    }

    fun numDistinctDailyCreations() : Int {
        return dailyCreations.size
    }

    fun save() {
        val serializer = JsonSerializer<TypeSpecificData> { typeSpecificData: TypeSpecificData, _, jsonSerializationContext: JsonSerializationContext ->
            val json = JsonObject()

            json.addProperty(typeSpecificData::creationType.name, typeSpecificData.creationType.toString())

            when (typeSpecificData) {
                is ArcanismData -> {
                    json.add(typeSpecificData::validSpells.name, jsonSerializationContext.serialize(typeSpecificData.validSpells, object: TypeToken<ArrayList<String>>() {}.type))
                }
            }

            json
        }
        val gson: Gson = GsonBuilder().registerTypeAdapter(typeSpecificDataType, serializer).create()

        val str = gson.toJson(dailyCreations)

        val context: Context = ArtisanApplication.getContext()
        val filename = "creations.json"
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(str.toByteArray())
        }
    }


    fun load() {
        var deserializer = JsonDeserializer<TypeSpecificData> { jsonElement: JsonElement, _, jsonDeserializationContext: JsonDeserializationContext ->
            val jsonObj = jsonElement.asJsonObject
            var result: TypeSpecificData

            val creationType: TypeSpecificData.CreationType = TypeSpecificData.CreationType.valueOf(jsonObj.get(TypeSpecificData::creationType.name).asString)
            if (creationType == TypeSpecificData.CreationType.Arcanism || jsonObj.has(ArcanismData::validSpells.name)) {
                var spells: ArrayList<String> = ArrayList()
                if (jsonObj.has(ArcanismData::validSpells.name)) {
                    val jsonArr = jsonObj.get(ArcanismData::validSpells.name)
                    spells = jsonDeserializationContext.deserialize(jsonArr, object: TypeToken<ArrayList<String>>() {}.type)
                }

                result = ArcanismData(spells)
            } else {
                result = TypeSpecificData(creationType)
            }

            result
        }
        val gson = GsonBuilder().registerTypeAdapter(typeSpecificDataType, deserializer).create()
        //val gson = Gson()

        val context: Context = ArtisanApplication.getContext()
        val filename = "creations.json"

        val inputStream = context.openFileInput(filename)
        val reader = BufferedReader(InputStreamReader(inputStream))
        //val text = reader.readText()
        //Log.d(TAG, text)

        dailyCreations = gson.fromJson(reader, dailyCreationsType)
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

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)
}