package com.example.newsapp_praxisphase.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.newsapp_praxisphase.models.Article
@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getArticles() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}