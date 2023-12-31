package com.example.newsapp_praxisphase.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp_praxisphase.repository.NewsRepository

class NewsViewModelFactory(private val newsRepository: NewsRepository) : ViewModelProvider.Factory {
  fun <T :ViewModel?> create(modelClass: Class<T>): T {
      return NewsViewModel(newsRepository) as T
   }
}
