package com.example.earthquake_app.api

import com.example.earthquake_app.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface EqApiService {
    @GET(Constants.GET_LAST_HOUR_URL) //This is the endpoint
    suspend fun getLastHourEarthquakes(): EqJsonResponse
}
private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
var service: EqApiService = retrofit.create<EqApiService>(EqApiService::class.java)