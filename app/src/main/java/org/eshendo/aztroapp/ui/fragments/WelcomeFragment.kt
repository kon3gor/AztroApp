package org.eshendo.aztroapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import org.eshendo.aztroapp.R
import org.eshendo.aztroapp.callback.BeginCallback
import org.eshendo.aztroapp.databinding.FragmentWelcomeBinding


class WelcomeFragment(
    private val callback: BeginCallback
) : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        binding.begin.setOnClickListener {
            callback.toNextFragment(binding.welcome, binding.img, binding.begin)
        }

        return binding.root
    }
}