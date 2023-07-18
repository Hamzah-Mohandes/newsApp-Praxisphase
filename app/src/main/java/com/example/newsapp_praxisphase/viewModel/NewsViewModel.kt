package com.example.newsapp_praxisphase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.newsapp_praxisphase.models.Article
import com.example.newsapp_praxisphase.models.NewsResponse
import com.example.newsapp_praxisphase.repository.NewsRepository
import com.example.newsapp_praxisphase.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
): ViewModel() {
    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingPageNumber = 1
    var breakingNewsResponse : NewsResponse? = null

    // for searching News
    val searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchPageNumber = 1
    var searchNewsResponse : NewsResponse? = null

lateinit var articles : LiveData<PagedList<Article>>
init {
    getBreakingNews("in")
}

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val responce = newsRepository.getBreakingNews(countryCode, breakingPageNumber)
        breakingNews.postValue(handleBreakingNewsResponce(responce))
    }

    private fun handleBreakingNewsResponce(responce: Response<NewsResponse>): Resource<NewsResponse>? {
if (responce.isSuccessful){
    responce.body()?.let {resultResponce ->
        searchPageNumber++
        if (searchNewsResponse == null){
            searchNewsResponse = resultResponce
        }else {
            val oldArticles = searchNewsResponse?.articles
            val newArticles = resultResponce.articles
            oldArticles?.addAll(newArticles)
        }
return Resource.Success(searchNewsResponse ?: resultResponce)
    }
}
        return Resource.Error(responce.message())

    }
fun getSearchedNews(queryString: String) = viewModelScope.launch {
    searchNews.postValue(Resource.Loading())
    val searchNewsResponce = newsRepository.getSearchNews(queryString, searchPageNumber)
    searchNews.postValue(handleSearchgNewsResponce(searchNewsResponce))
}

    private fun handleSearchgNewsResponce(responce: Response<NewsResponse>): Resource<NewsResponse>? {
if (responce.isSuccessful){
    responce.body().let {resultResponse ->
        searchPageNumber++
        if (searchNewsResponse == null){
            searchNewsResponse = resultResponse
        }else {
           val oldArticle = searchNewsResponse?.articles
            val newArticles = resultResponse!!.articles
            oldArticle?.addAll(newArticles)
        }
        return Resource.Success(searchNewsResponse ?: resultResponse!! )
    }
}
        return Resource.Error(responce.message( ))
    }
fun insertArticle(article: Article) = viewModelScope.launch {
    newsRepository.upsert(article)
}
    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSaveArticles() = newsRepository.getAllArticles()
    fun getBreakingArticles() : LiveData<PagedList<Article>>{
        return articles
    }
}