package com.lab4.task6.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.lab4.task6.databinding.FragmentNewsInfoBinding

class NewsInfoFragment : Fragment()
{
    private var _binding: FragmentNewsInfoBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentNewsInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        val currentNews = NewsInfoFragmentArgs.fromBundle(requireArguments()).currentNews

        binding.news = currentNews
        binding.lifecycleOwner = viewLifecycleOwner

        binding.webView.webViewClient = object : WebViewClient()
        {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean
            {
                super.shouldOverrideUrlLoading(view, request)
                return false
            }
        }

        binding.webView.loadUrl(currentNews.link)

        return view
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}