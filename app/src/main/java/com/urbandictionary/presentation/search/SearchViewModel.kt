package com.urbandictionary.presentation.search

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urbandictionary.data.model.UrbanDictionaryResponse
import com.urbandictionary.domain.model.ApiError
import com.urbandictionary.domain.usecase.GetPostsUseCase
import com.urbandictionary.domain.usecase.base.UseCaseResponse

class SearchViewModel constructor(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {

    val postsData = MutableLiveData<UrbanDictionaryResponse>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getDefine(term: String) {
        showProgressbar.value = true
        getPostsUseCase.invoke(
            viewModelScope, term,
            object :
                UseCaseResponse<UrbanDictionaryResponse> {
                override fun onSuccess(result: UrbanDictionaryResponse) {
                    Log.i(TAG, "result: $result")
                    postsData.value = result
                    showProgressbar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    showProgressbar.value = false
                }
            },
        )
    }


    companion object {
        private val TAG = SearchViewModel::class.java.name
    }


    val showLoading = ObservableBoolean()
}
