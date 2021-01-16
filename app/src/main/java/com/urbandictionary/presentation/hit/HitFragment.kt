package com.urbandictionary.presentation.hit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.urbandictionary.R
import com.urbandictionary.databinding.FragmentHitBinding
import com.urbandictionary.presentation.search.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HitFragment : Fragment() {

    private lateinit var binding: FragmentHitBinding
    private val viewModel: SearchViewModel by viewModel()

    private var url: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_hit, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }
        }
        binding.webview.loadUrl(url)
    }
}