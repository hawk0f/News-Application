package com.lab4.task6.viewModels

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.lab4.task6.viewsPhone.NewsInfoPhoneFragmentArgs

class NewsInfoViewModel(bundle: Bundle) : ViewModel()
{
    val currentNews = NewsInfoPhoneFragmentArgs.fromBundle(bundle).currentNews
}