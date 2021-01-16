package com.urbandictionary.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.urbandictionary.data.model.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun all(): LiveData<List<Article>>

    @Query("SELECT * FROM article WHERE id=:articleId")
    fun getById(articleId: Long): LiveData<Article>

    @get:Query("SELECT count(*) FROM article")
    val count: Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg hits: Article)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(article: Article)

}