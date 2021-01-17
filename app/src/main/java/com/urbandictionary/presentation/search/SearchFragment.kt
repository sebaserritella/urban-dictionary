package com.urbandictionary.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.urbandictionary.R
import com.urbandictionary.databinding.FragmentSearchBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private var mAdapter: UrbanAdapter? = UrbanAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.urbanDictionaryList.adapter = mAdapter
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            binding.vm?.getDefine("perro")
        }

        binding.vm?.postsData?.observe(this.viewLifecycleOwner, { dictionaryResponse ->
            binding.progressBar.visibility = GONE
            dictionaryResponse.list?.let { mAdapter?.urbanList = it }
        })

    }

}