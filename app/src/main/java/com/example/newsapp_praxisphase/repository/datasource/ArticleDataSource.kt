package com.example.newsapp_praxisphase.repository.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.newsapp_praxisphase.models.Article
import com.example.newsapp_praxisphase.models.NewsResponse
import com.example.newsapp_praxisphase.repository.service.RetrofitClient
import com.example.newsapp_praxisphase.utils.Constants
import com.example.newsapp_praxisphase.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ArticleDataSource (val scope : CoroutineScope): PageKeyedDataSource<Int , Article>() {
    val breakingNews : MutableLiveData<MutableList<Article>> = MutableLiveData()
    var breakingPageNumber = 1
    var breakingNewsResponse : NewsResponse? = null

    // for searching News
    val searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchPageNumber = 1
    var searchNewsResponse : NewsResponse? = null

override fun loadInitial(
    pramas : LoadInitialParams<Int>,
    callback : LoadInitialCallback<Int,Article>
){
    scope.launch{
        try {
            val  response = RetrofitClient.api.getBreakingNews("ge",1,Constants.API_KEY)
            when{
                response.isSuccessful->{
                    response.body()?.articles?.let {
                        breakingNews.postValue(it)
                        callback.onResult(it,null,2)
                    }
                }
            }
        }catch (exception : Exception){
Log.e("DataSource::", exception.message.toString())
        }

    }
}




    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        try { scope.launch {
            val  response = RetrofitClient.api.getBreakingNews("ge",params.requestedLoadSize,Constants.API_KEY)
            when{
                response.isSuccessful->{
                    response.body()?.articles?.let {

                        callback.onResult(it,params.key+1)
                    }
                }
            } }

        }catch (exception : Exception){
            Log.e("DataSource::",exception.message.toString() )
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

    }
}