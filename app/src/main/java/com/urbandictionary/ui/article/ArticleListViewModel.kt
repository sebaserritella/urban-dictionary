package com.urbandictionary.ui.article

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbandictionary.base.AsyncViewModel
import com.urbandictionary.data.model.Article
import com.urbandictionary.data.repository.ArticleRepository
import kotlinx.coroutines.launch

class ArticleListViewModel(
    private val articleRepository: ArticleRepository
) : AsyncViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadArticles() }

    val articles: LiveData<List<Article>> = articleRepository.getArticles()

    init {
        loadingVisibility.value = View.GONE
        loadArticles()
    }

    fun loadArticles() {
        launch {
            if (articleRepository.getCachedCount() == 0) {
                loadingVisibility.value = View.VISIBLE
            }

            articleRepository.refreshArticles()

            loadingVisibility.value = View.GONE
        }
    }

    fun refresh() {
        launch {
            articleRepository.refreshArticles()

            loadingVisibility.value = View.GONE
        }
    }
}