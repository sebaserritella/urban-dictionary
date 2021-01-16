package com.urbandictionary.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SplashFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToArticleListFragment())
    }


}