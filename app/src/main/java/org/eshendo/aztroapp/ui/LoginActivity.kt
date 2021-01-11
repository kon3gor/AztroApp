package org.eshendo.aztroapp.ui

import android.animation.ObjectAnimator
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import org.eshendo.aztroapp.R
import org.eshendo.aztroapp.callback.BeginCallback
import org.eshendo.aztroapp.databinding.ActivityLoginBinding
import org.eshendo.aztroapp.ui.fragments.SignFragment
import org.eshendo.aztroapp.ui.fragments.WelcomeFragment

class LoginActivity : AppCompatActivity(), BeginCallback {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var welcome: WelcomeFragment
    private lateinit var enter: SignFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        welcome = WelcomeFragment(this)
        enter = SignFragment()

        switchFragment(welcome)
    }

    private fun switchFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun toNextFragment(title: View, img: View, btn: View) {
        ObjectAnimator.ofFloat(title, "translationY", -1000f).apply {
            duration = 300
            start()
        }

        ObjectAnimator.ofFloat(img, "translationY", 1000f).apply {
            duration = 300
            start()
        }

        ObjectAnimator.ofFloat(btn, "translationX", 1000f).apply {
            duration = 300
            start()
            doOnEnd {
                switchFragment(enter)
            }
        }
    }
}