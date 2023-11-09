package com.lab4.task6.viewsPhone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lab4.task6.databinding.FragmentNewsInfoPhoneBinding
import com.lab4.task6.viewModelsFactories.NewsInfoViewModelFactory
import com.lab4.task6.viewModels.NewsInfoViewModel

class NewsInfoPhoneFragment : Fragment()
{
    private var _binding: FragmentNewsInfoPhoneBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: NewsInfoViewModel
    private lateinit var viewModelFactory: NewsInfoViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentNewsInfoPhoneBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModelFactory = NewsInfoViewModelFactory(requireArguments())
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsInfoViewModel::class.java]

        binding.currentNews = viewModel.currentNews

        binding.webView.webViewClient = object : WebViewClient()
        {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean
            {
                super.shouldOverrideUrlLoading(view, request)
                return false
            }
        }

        binding.webView.loadUrl(viewModel.currentNews.url)

        return view
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}