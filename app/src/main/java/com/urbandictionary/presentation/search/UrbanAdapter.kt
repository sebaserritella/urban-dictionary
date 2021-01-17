package com.urbandictionary.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.urbandictionary.R
import com.urbandictionary.data.model.Urban
import com.urbandictionary.databinding.HolderUrbanBinding
import kotlin.properties.Delegates

class UrbanAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var urbanList: List<Urban> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPostBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_urban, parent, false
        )
        return UrbanViewHolder(holderPostBinding)
    }

    override fun getItemCount(): Int = if (urbanList.isNullOrEmpty()) 0 else urbanList.size

    private fun getItem(position: Int): Urban = urbanList[position]

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