package com.example.newsapp_praxisphase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp_praxisphase.repository.NewsRepository
import com.example.newsapp_praxisphase.repository.db.ArticleDatabase
import com.example.newsapp_praxisphase.viewModel.NewsViewModel
import com.example.newsapp_praxisphase.viewModel.NewsViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProvider = NewsViewModelFactory(newsRepository)

        viewModel = ViewModelProvider(this, viewModelProvider).get(NewsViewModel::class.java)


    }
}