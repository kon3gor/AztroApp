package org.eshendo.aztroapp.network

import org.eshendo.aztroapp.model.Day
import org.eshendo.aztroapp.model.Horoscope
import org.eshendo.aztroapp.model.HoroscopeRequest
import org.eshendo.aztroapp.util.Constants
import retrofit2.Call
import retrofit2.http.*

interface AztroApi {
    @POST("/")
    @Headers(
        Constants.HEADER1,
        Constants.HEADER2
    )
    fun getHoroscope(
        @Query("sign") sign: String,
        @Query("day") day: Day
    ) : Call<Horoscope>
}