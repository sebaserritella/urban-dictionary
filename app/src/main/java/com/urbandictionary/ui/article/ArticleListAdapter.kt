package com.urbandictionary.ui.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.urbandictionary.R
import com.urbandictionary.data.model.Hit
import com.urbandictionary.databinding.ItemArticleBinding

class ArticleListAdapter : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    var hitList: MutableList<Hit>? = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_article,
                parent,
                false
            )
        )

    fun removeAt(position: Int) {
        hitList?.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hit = hitList?.get(position) ?: return
        holder.bind(hit)

        holder.itemView.setOnClickListener { view ->

        }
    }

    override fun getItemCount() = hitList?.size ?: 0

    inner class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hit: Hit) {
            binding.hit = hit
        }
    }
}