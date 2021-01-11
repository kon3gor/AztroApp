package org.eshendo.aztroapp.network

import android.util.Log
import org.eshendo.aztroapp.callback.ApiCallback
import org.eshendo.aztroapp.model.Horoscope
import org.eshendo.aztroapp.model.HoroscopeRequest
import org.eshendo.aztroapp.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Requester {

    private val service = Retrofit.Builder()
        .baseUrl(Constants.URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(AztroApi::class.java)

    fun requestHoroscope(body: HoroscopeRequest, callback: ApiCallback){
        service.getHoroscope(body.sign, body.day).enqueue(object : Callback<Horoscope>{
            override fun onResponse(call: Call<Horoscope>, response: Response<Horoscope>) {
                if (response.isSuccessful){
                    callback.onResponse(response.body()!!)
                }else{
                    callback.onError("${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Horoscope>, t: Throwable) {
                callback.onError("check logs")
                Log.e("duck", "", t)
            }
        })
    }
}