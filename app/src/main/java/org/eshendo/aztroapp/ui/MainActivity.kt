package org.eshendo.aztroapp.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.eshendo.aztroapp.R
import org.eshendo.aztroapp.callback.ApiCallback
import org.eshendo.aztroapp.databinding.ActivityMainBinding
import org.eshendo.aztroapp.model.Day
import org.eshendo.aztroapp.model.Horoscope
import org.eshendo.aztroapp.model.HoroscopeRequest
import org.eshendo.aztroapp.model.Sign
import org.eshendo.aztroapp.network.Requester
import java.util.*

class  MainActivity : AppCompatActivity(), ApiCallback {

    private lateinit var binding: ActivityMainBinding

    private var day = Day.TODAY
    private var sign: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        requestHoroscope(true)
        attachListeners()
    }

    private fun requestHoroscope(firstTime: Boolean){
        if (firstTime){
            sign = getAndSetSign()
        }
        val requestBody = HoroscopeRequest(sign!!, day)
        binding.topbar.day.text = day.day.capitalize(Locale.getDefault())
        Requester.requestHoroscope(requestBody, this)

    }

    private fun attachListeners(){
        binding.topbar.next.setOnClickListener { arrowClicked(true) }
        binding.topbar.prev.setOnClickListener { arrowClicked(false) }
    }

    private fun arrowClicked(direction: Boolean){
        when(day){
            Day.TODAY -> {
                day = if (direction) Day.TOMORROW else Day.YESTERDAY
                requestHoroscope(false)
            }
            Day.YESTERDAY-> {
                if (direction){
                    day = Day.TODAY
                    requestHoroscope(false)
                }
            }
            Day.TOMORROW ->{
                if (!direction){
                    day = Day.TODAY
                    requestHoroscope(false)
                }
            }
        }
    }

    private fun getAndSetSign() : String{
        val sign = applicationContext.getSharedPreferences("app", MODE_PRIVATE).getString("sign", "")!!
        val res = applicationContext.getSharedPreferences("app", MODE_PRIVATE).getInt("res", R.drawable.ic_cancel)
        binding.icon.setImageDrawable(ContextCompat.getDrawable(this, res))

        return sign
    }

    private fun setHoroscope(horoscope: Horoscope){
        binding.desc.text = horoscope.description
        binding.grid.compatibility.text = horoscope.compatibility
        binding.grid.luckyNumber.text = horoscope.luckyNumber
        binding.grid.luckyTime.text = horoscope.luckyTime
        binding.grid.mood.text = horoscope.mood
        binding.topbar.range.text = horoscope.dateRange
    }

    override fun onResponse(horoscope: Horoscope) {
        setHoroscope(horoscope)
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}