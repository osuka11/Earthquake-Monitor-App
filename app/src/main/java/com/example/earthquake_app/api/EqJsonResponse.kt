package com.example.earthquake_app.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EqJsonResponse(val features: List<Features>) {
}