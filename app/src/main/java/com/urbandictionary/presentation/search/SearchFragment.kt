package com.urbandictionary.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.urbandictionary.R
import com.urbandictionary.databinding.FragmentSearchBinding
import com.urbandictionary.util.isNetworkAvailable
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
        binding.apply {
            lifecycleOwner = this@SearchFragment
            vm = viewModel
            urbanDictionaryList.adapter = mAdapter
        }

        if (this.context?.isNetworkAvailable() == false) {
            Toast.makeText(
                this.context,
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            binding.vm?.getDefine(binding.textInputEditText.text.toString())
        }

        binding.downSortButton.setOnClickListener {
            binding.vm?.sortDown()
        }

        binding.upSortButton.setOnClickListener {
            binding.vm?.sortUp()
        }

        binding.vm?.resultDictionaryData?.observe(this.viewLifecycleOwner, { dictionaryResponse ->
            binding.progressBar.visibility = GONE
            dictionaryResponse?.let { mAdapter?.urbanList = it }
        })

        binding.vm?.messageData?.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.context, it, LENGTH_LONG).show()
        })

        binding.vm?.showProgressbar?.observe(this.viewLifecycleOwner, Observer { isVisible ->
           binding.progressBar.visibility = if (isVisible) VISIBLE else GONE
        })

    }

}