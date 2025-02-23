package com.example.earthquake_app.main

import com.example.earthquake_app.Earthquake
import com.example.earthquake_app.api.EqJsonResponse
import com.example.earthquake_app.api.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository {

    suspend fun fetchEarthquakes():MutableList<Earthquake> {
        return withContext(Dispatchers.IO){
            val eqJsonResponse = service.getLastHourEarthquakes()
            val eqList = parseEqResult(eqJsonResponse)
            eqList
        }

    }

    private fun parseEqResult(eqJsonResponse: EqJsonResponse): MutableList<Earthquake> {
        val eqList = mutableListOf<Earthquake>()

        val featuresList = eqJsonResponse.features
        for (feature in featuresList){
            val id = feature.id
            val properties = feature.properties

            val magnitude = properties.mag
            val place = properties.place
            val time = properties.time

            val geometry = feature.geometry
            val longitude = geometry.longitude
            val latitude = geometry.latitude
            eqList.add(Earthquake(id,place,magnitude,time,longitude,latitude))

        }
        /*
        val eqJsonObject = JSONObject(eqListString)
        val featuresJsonArray = eqJsonObject.getJSONArray("features")

        for (i in 0 until featuresJsonArray.length()){
            val featuresJsonObject = featuresJsonArray[i] as JSONObject
            val id = featuresJsonObject.getString("id")
            val propertiesJsonObject = featuresJsonObject.getJSONObject("properties")

            val magnitude = propertiesJsonObject.getDouble("mag")
            val place = propertiesJsonObject.getString("place")
            val time = propertiesJsonObject.getLong("time")

            val geometryJsonObject = featuresJsonObject.getJSONObject("geometry")
            val coordinatesJsonArray = geometryJsonObject.getJSONArray("coordinates")

            val longitude = coordinatesJsonArray.getDouble(0)
            val latitude = coordinatesJsonArray.getDouble(1)


        }

         */

        return eqList

    }
}