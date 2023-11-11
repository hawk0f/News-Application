package com.lab4.task6.newsApi

import retrofit2.Call
import retrofit2.http.GET

interface NewsApi
{
    @GET("news?country=ru&apikey=pub_32748426f612f2d3ec0a704d949d022e76d52")
    fun getNews(): Call<JsonResponse>
}