package com.example.earthquake_app.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Features(val id: String, val properties: Properties, val geometry: Geometry) {
}