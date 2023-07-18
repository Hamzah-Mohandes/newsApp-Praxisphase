package com.example.newsapp_praxisphase.repository.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.newsapp_praxisphase.models.Article
import kotlinx.coroutines.CoroutineScope


class ArticleDtatSourceFactory(private val scope: CoroutineScope): DataSource.Factory<Int, Article>() {
    val articleDataSourceLiveData = MutableLiveData <ArticleDataSource>()
    override fun create (): DataSource<Int,Article> {
        val newArticleDataSource = ArticleDataSource(scope)
        articleDataSourceLiveData.postValue(newArticleDataSource)
        return newArticleDataSource
    }
}