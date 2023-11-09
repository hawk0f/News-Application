package com.lab4.task6.newsApi

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.lab4.task6.models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient
{
    private const val BASE_URL = "https://newsapi.org/v2/"

    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit
    {
        if (retrofit == null)
        {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }

        return retrofit!!
    }
}