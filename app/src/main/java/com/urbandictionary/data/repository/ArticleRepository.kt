package com.urbandictionary.data.repository

import com.urbandictionary.data.database.ArticleDao
import com.urbandictionary.data.network.HitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class ArticleRepository(private val articleDao: ArticleDao, private val hitApi: HitApi) {

    fun getArticles() = articleDao.all()

    suspend fun getCachedCount() = withContext(Dispatchers.IO) {
        articleDao.count
    }

    suspend fun refreshArticles() {
        withContext(Dispatchers.IO) {
            try {
                val article = hitApi.getArticleByDateAsync().await()
                articleDao.insert(article)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}