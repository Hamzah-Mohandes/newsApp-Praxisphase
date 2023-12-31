package com.example.newsapp_praxisphase.repository

import com.example.newsapp_praxisphase.models.Article
import com.example.newsapp_praxisphase.repository.db.ArticleDatabase
import com.example.newsapp_praxisphase.repository.service.RetrofitClient

class NewsRepository
    (
    val db : ArticleDatabase
            )
{
        suspend fun getBreakingNews(countryCode : String , pageNumber: Int) =
            RetrofitClient.api.getBreakingNews(countryCode,pageNumber)

    suspend fun getSearchNews(q : String , pageNumber: Int) =
        RetrofitClient.api.getSearchNews(q , pageNumber)

    suspend fun upsert(article : Article) = db.getArticleDoa().insert(article)
    suspend fun delete(article : Article) = db.getArticleDoa().insert(article)

    fun getAllArticles() = db.getArticleDoa().getArticles()
}