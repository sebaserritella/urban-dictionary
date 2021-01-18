package com.urbandictionary.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.urbandictionary.R
import com.urbandictionary.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        binding.apply {
            lifecycleOwner = this@SplashFragment
        }

        val slideAnimation = AnimationUtils.loadAnimation(this.context, R.anim.side_slide)
        binding.SplashScreenImage.startAnimation(slideAnimation)

        Handler().postDelayed({
            this.findNavController()
                .navigate(SplashFragmentDirections.actionSplashFragmentToSearchFragment())
        }, 3000) // 3000 is the delayed time in milliseconds.

        return binding.root
    }

}