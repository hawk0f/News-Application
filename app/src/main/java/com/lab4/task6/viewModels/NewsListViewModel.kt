package com.lab4.task6.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab4.task6.models.News
import com.lab4.task6.newsApi.JsonResponse
import com.lab4.task6.newsApi.NewsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListViewModel(private val newsApi: NewsApi) : ViewModel()
{
    private val _newsList: MutableLiveData<MutableList<News>> = MutableLiveData<MutableList<News>>()
    val newsList: LiveData<MutableList<News>>
        get() = _newsList

    private val _currentNews = MutableLiveData<News?>()
    val currentNews: LiveData<News?>
        get() = _currentNews

    init
    {
        loadData()
    }

    fun onNewsClicked(news: News)
    {
        _currentNews.value = news
    }

    fun onNewsNavigated()
    {
        _currentNews.value = null
    }

    private fun loadData()
    {
        newsApi.getNews().enqueue(object : Callback<JsonResponse>
        {
            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>)
            {
                if (response.isSuccessful)
                {
                    _newsList.value = response.body()!!.results as MutableList<News>
                }
                else
                {
                    Toast.makeText(viewModelScope.coroutineContext as Context, response.errorBody().toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable)
            {
                Toast.makeText(viewModelScope.coroutineContext as Context, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}