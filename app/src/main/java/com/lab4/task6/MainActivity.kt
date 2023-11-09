package com.lab4.task6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lab4.task6.adapters.TabletNewsAdapter
import com.lab4.task6.databinding.ActivityMainBinding
import com.lab4.task6.models.News
import com.lab4.task6.viewModels.MainActivityViewModel
import com.lab4.task6.viewModelsFactories.MainActivityViewModelFactory
import com.lab4.task6.viewsTablet.NewsInfoTabletFragment
import com.lab4.task6.viewsTablet.NewsListTabletFragment


class MainActivity : AppCompatActivity()
{
    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModelFactory = MainActivityViewModelFactory(applicationContext)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]

        viewModel.newsList.observe(this) { _ ->
            val newsListFragment = binding.newsListFragmentContainerView!!.getFragment<NewsListTabletFragment>()
            val recyclerView = newsListFragment.requireView().findViewById<RecyclerView>(R.id.newsRecycler)

            val onNewsClickListener: TabletNewsAdapter.OnNewsClickListener = object : TabletNewsAdapter.OnNewsClickListener
            {
                override fun onNewsClick(news: News, newsList: MutableList<News>)
                {
                    newsList.forEach {
                        it.isSelected = false
                    }
                    news.isSelected = true
                    viewModel.currentNews.value = news
                    viewModel.newsList.value = newsList
                }
            }

            val newsAdapter = TabletNewsAdapter(viewModel, onNewsClickListener)
            recyclerView.adapter = newsAdapter
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.currentNews.observe(this) { newValue ->
            supportFragmentManager.beginTransaction().replace(R.id.newsInfoFragmentContainerView, NewsInfoTabletFragment.newInstance(newValue)).commit()
            binding.newsInfoFragmentContainerView!!.findViewById<RatingBar>(R.id.ratingBar)?.setOnRatingBarChangeListener { _, _, _ ->
                (binding.newsListFragmentContainerView!!.findViewById<RecyclerView>(R.id.newsRecycler).adapter as TabletNewsAdapter).updateNews(newValue, viewModel.newsList.value!!.indexOf(viewModel.currentNews.value))
                (binding.newsListFragmentContainerView!!.findViewById<RecyclerView>(R.id.newsRecycler).adapter as TabletNewsAdapter).updateNewsList(viewModel.newsList.value!!)
            }
        }
    }
}