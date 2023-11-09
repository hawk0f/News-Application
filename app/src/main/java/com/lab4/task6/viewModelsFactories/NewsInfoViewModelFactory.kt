package com.lab4.task6.viewModelsFactories

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lab4.task6.viewModels.NewsInfoViewModel

class NewsInfoViewModelFactory(private val bundle: Bundle) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(NewsInfoViewModel::class.java))
        {
            return NewsInfoViewModel(bundle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}