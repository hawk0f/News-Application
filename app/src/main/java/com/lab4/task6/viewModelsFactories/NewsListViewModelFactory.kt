package com.lab4.task6.viewModelsFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lab4.task6.newsApi.NewsApi
import com.lab4.task6.viewModels.NewsListViewModel

class NewsListViewModelFactory(private val newsApi: NewsApi) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java))
        {
            return NewsListViewModel(newsApi) as T
        }
        throw IllegalArgumentException("Unknown viewModel!")
    }
}