package com.urbandictionary.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.urbandictionary.R
import com.urbandictionary.databinding.HolderUrbanBinding
import com.urbandictionary.domain.model.Urban
import kotlin.properties.Delegates

class UrbanAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var urbanApiModelList: List<Urban> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPostBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_urban, parent, false
        )
        return UrbanViewHolder(holderPostBinding)
    }

    override fun getItemCount(): Int =
        if (urbanApiModelList.isNullOrEmpty()) 0 else urbanApiModelList.size

    private fun getItem(position: Int): Urban = urbanApiModelList[position]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UrbanViewHolder).onBind(getItem(position))
    }

    private inner class UrbanViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun onBind(urban: Urban) {
            (viewDataBinding as HolderUrbanBinding).urban = urban
        }
    }
}