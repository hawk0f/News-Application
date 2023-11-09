package com.lab4.task6.viewsTablet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lab4.task6.R
import com.lab4.task6.databinding.FragmentNewsListTabletBinding

class NewsListTabletFragment : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        return inflater.inflate(R.layout.fragment_news_list_tablet, container, false)
    }
}