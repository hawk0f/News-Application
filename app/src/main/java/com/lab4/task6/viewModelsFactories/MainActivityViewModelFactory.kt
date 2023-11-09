package com.lab4.task6.viewModelsFactories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lab4.task6.viewModels.MainActivityViewModel

class MainActivityViewModelFactory(private val context: Context) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java))
        {
            return MainActivityViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}