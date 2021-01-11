package org.eshendo.aztroapp.ui.fragments

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import org.eshendo.aztroapp.R
import org.eshendo.aztroapp.databinding.FragmentSignBinding
import org.eshendo.aztroapp.model.Sign
import org.eshendo.aztroapp.ui.MainActivity
import java.util.*

class SignFragment : Fragment() {

    private lateinit var binding: FragmentSignBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignBinding.inflate(inflater, container, false)

        binding.next.setOnClickListener {
            val sign = binding.enter.text!!.toString().trim().toLowerCase(Locale.getDefault())
            saveSign(sign)
            animateExit()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ObjectAnimator.ofFloat(binding.title, "translationY", -1000f, 0f).apply {
            duration = 300
            start()
        }

        ObjectAnimator.ofFloat(binding.img, "translationY", 1000f, 0f).apply {
            duration = 300
            start()
        }

        ObjectAnimator.ofFloat(binding.enter, "translationX", -1000f, 0f).apply {
            duration = 300
            start()
        }

        ObjectAnimator.ofFloat(binding.next, "translationX", -1000f, 0f).apply {
            duration = 300
            start()
        }
    }

    private fun saveSign(sign: String) {
        val sign = sign.trim('.', ' ', ',', ';', ':', '"', '%', '$', '^', '&', '*', '#', '@', '!' )
        if (!checkSign(sign)){
            Toast.makeText(context, "There is no such a sign.", Toast.LENGTH_SHORT).show()
            return
        }
        activity!!.applicationContext.getSharedPreferences("app", Activity.MODE_PRIVATE)
            .edit()
            .putString("sign", sign)
            .putInt("res", getResourceBySign(sign))
            .apply()

    }

    private fun checkSign(sign: String) : Boolean{
        val signs = listOf(
            "aries",
            "taurus",
            "gemini",
            "cancer",
            "leo",
            "virgo",
            "libra",
            "scorpio",
            "sagittarius",
            "capricorn",
            "aquarius",
            "pisces"
        )
        return signs.contains(sign)
    }

    private fun getResourceBySign(sign: String) : Int{
        return when(sign){
            "aries" -> Sign.Aries.resource
            "taurus" -> Sign.Taurus.resource
            "gemini" -> Sign.Gemini.resource
            "cancer" -> Sign.Cancer.resource
            "leo" -> Sign.Leo.resource
            "virgo" -> Sign.Virgo.resource
            "libra" -> Sign.Libra.resource
            "scorpio" -> Sign.Scorpio.resource
            "sagittarius" -> Sign.Sagittarius.resource
            "capricorn" -> Sign.Capricorn.resource
            "aquarius" -> Sign.Aquarius.resource
            "pisces" -> Sign.Pisces.resource
            else -> R.drawable.ic_cancel
        }
    }

    private fun animateExit(){
        ObjectAnimator.ofFloat(binding.title, "translationY", 0f, -1000f).apply {
            duration = 300
            start()
        }

        ObjectAnimator.ofFloat(binding.img, "translationY", 0f, 1000f).apply {
            duration = 300
            start()
        }

        ObjectAnimator.ofFloat(binding.enter, "translationX", 0f, -1000f).apply {
            duration = 300
            start()
        }

        ObjectAnimator.ofFloat(binding.next, "translationX", 0f, -1000f).apply {
            duration = 300
            start()
            doOnEnd {
                val i = Intent(context!!, MainActivity::class.java)
                startActivity(i)
                activity!!.finish()
            }
        }
    }

}