package com.lab4.task6.viewModels

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.lab4.task6.views.NewsInfoFragmentArgs

class NewsInfoViewModel(bundle: Bundle) : ViewModel()
{
    val currentNews = NewsInfoFragmentArgs.fromBundle(bundle).currentNews
}