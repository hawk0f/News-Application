package com.lab4.task6.viewsPhone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lab4.task6.adapters.PhoneNewsAdapter
import com.lab4.task6.databinding.FragmentNewsListPhoneBinding
import com.lab4.task6.viewModels.NewsListViewModel
import com.lab4.task6.viewModelsFactories.NewsListViewModelFactory

class NewsListPhoneFragment : Fragment()
{
    private var _binding: FragmentNewsListPhoneBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: NewsListViewModel
    private lateinit var viewModelFactory: NewsListViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentNewsListPhoneBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModelFactory = NewsListViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsListViewModel::class.java]

        viewModel.newsList.observe(viewLifecycleOwner) { _ ->
            val newsAdapter = PhoneNewsAdapter(viewModel)
            binding.newsRecycler.adapter = newsAdapter
            binding.newsRecycler.layoutManager = LinearLayoutManager(context)
        }

        return view
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}