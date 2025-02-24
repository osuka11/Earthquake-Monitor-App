package com.example.earthquake_app.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Properties(val mag: Double, val place:String, val time:Long) {
}