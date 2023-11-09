package com.lab4.task6.newsApi

import retrofit2.Call
import retrofit2.http.GET

interface NewsApi
{
    @GET("everything?q=keyword&apiKey=d8b2762d67374afabd14b81c12bf833b")
    fun getNews(): Call<JsonResponse>
}