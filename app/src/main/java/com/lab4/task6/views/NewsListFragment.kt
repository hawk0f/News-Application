package com.lab4.task6.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lab4.task6.adapters.NewsItemAdapter
import com.lab4.task6.databinding.FragmentNewsListBinding
import com.lab4.task6.newsApi.NewsApi
import com.lab4.task6.newsApi.RetrofitClient
import com.lab4.task6.viewModels.NewsListViewModel
import com.lab4.task6.viewModelsFactories.NewsListViewModelFactory

class NewsListFragment : Fragment()
{
    private var _binding: FragmentNewsListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        val view = binding.root

        val retrofitClient = RetrofitClient.getInstance()
        val apiService = retrofitClient.create(NewsApi::class.java)
        val viewModelFactory = NewsListViewModelFactory(apiService)
        val viewModel = ViewModelProvider(this, viewModelFactory)[NewsListViewModel::class.java]

        val newsAdapter = NewsItemAdapter { currentNews ->
            viewModel.onNewsClicked(currentNews)
        }
        binding.newsRecycler.adapter = newsAdapter

        viewModel.newsList.observe(viewLifecycleOwner) { newsList ->
            newsList?.let {
                newsAdapter.submitList(newsList)
            }
        }

        viewModel.currentNews.observe(viewLifecycleOwner) { news ->
            news?.let {
                val action = NewsListFragmentDirections.actionNewsListFragmentToWebViewFragment(news)
                this.findNavController().navigate(action)
                viewModel.onNewsNavigated()
            }
        }

        return view
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}