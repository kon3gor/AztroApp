package org.eshendo.aztroapp.callback

import org.eshendo.aztroapp.model.Horoscope

interface ApiCallback {
    fun onResponse(horoscope: Horoscope)
    fun onError(msg: String)
}