package org.eshendo.aztroapp.model


import com.squareup.moshi.Json

class Horoscope(
    val compatibility: String,
    @field:Json(name="date_range")
    val dateRange: String,
    val description: String,
    @field:Json(name = "lucky_number")
    val luckyNumber: String,
    @field:Json(name = "lucky_time")
    val luckyTime: String,
    val mood: String
)