package com.lab4.task6.viewsTablet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.lab4.task6.databinding.FragmentNewsInfoTabletBinding
import com.lab4.task6.models.News

class NewsInfoTabletFragment : Fragment()
{
    private var _currentNews: News? = null
    private val currentNews
        get() = _currentNews!!

    private var _binding: FragmentNewsInfoTabletBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {
            _currentNews = it.getSerializable("currentNews") as News
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentNewsInfoTabletBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.currentNews = currentNews

        binding.webView.webViewClient = object : WebViewClient()
        {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean
            {
                super.shouldOverrideUrlLoading(view, request)
                return false
            }
        }

        binding.webView.loadUrl(currentNews.url)

        return view
    }

    companion object
    {
        @JvmStatic
        fun newInstance(currentNews: News) = NewsInfoTabletFragment().apply {
            arguments = Bundle().apply {
                putSerializable("currentNews", currentNews)
            }
        }
    }
}