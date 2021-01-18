package com.urbandictionary.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    view.getParentActivity()?.let { parentActivity ->
        visibility?.observe(parentActivity, { view.visibility = it ?: View.VISIBLE })
    }
}