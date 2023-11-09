package com.lab4.task6.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lab4.task6.models.News
import com.lab4.task6.newsApi.JsonResponse
import com.lab4.task6.newsApi.NewsApi
import com.lab4.task6.newsApi.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(private val context: Context) : ViewModel()
{
    val newsList: MutableLiveData<MutableList<News>> = MutableLiveData<MutableList<News>>()

    val currentNews: MutableLiveData<News> = MutableLiveData<News>()

    init
    {
        loadData()
    }

    private fun loadData()
    {
        val retrofitClient = RetrofitClient.getInstance()
        val apiService = retrofitClient.create(NewsApi::class.java)

        apiService.getNews().enqueue(object : Callback<JsonResponse>
        {
            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>)
            {
                if (response.isSuccessful)
                {
                    newsList.value = response.body()!!.articles as MutableList<News>
                }
                else
                {
                    Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable)
            {
                Toast.makeText(context, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}