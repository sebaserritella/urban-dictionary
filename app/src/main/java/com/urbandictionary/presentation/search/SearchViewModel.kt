package com.urbandictionary.presentation.search

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urbandictionary.domain.model.ApiError
import com.urbandictionary.domain.model.Urban
import com.urbandictionary.domain.model.UrbanDictionaryResponse
import com.urbandictionary.domain.usecase.GetUrbanDictionaryUseCase
import com.urbandictionary.domain.usecase.base.UseCaseResponse

class SearchViewModel constructor(private val getUrbanDictionaryUseCase: GetUrbanDictionaryUseCase) :
    ViewModel() {

    val resultDictionaryData = MutableLiveData<List<Urban>>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getDefine(term: String) {
        showProgressbar.value = true
        getUrbanDictionaryUseCase.invoke(
            viewModelScope, term,
            object :
                UseCaseResponse<LiveData<UrbanDictionaryResponse>> {
                override fun onSuccess(result: LiveData<UrbanDictionaryResponse>) {
                    Log.i(TAG, "result: ${result.value}")
                    resultDictionaryData.value = result.value?.list
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

    fun sortDown() {
        resultDictionaryData.postValue(resultDictionaryData.value?.sortedByDescending { x -> x.thumbs_down })
    }

    fun sortUp() {
        resultDictionaryData.postValue(resultDictionaryData.value?.sortedByDescending { x -> x.thumbs_up })
    }
}
