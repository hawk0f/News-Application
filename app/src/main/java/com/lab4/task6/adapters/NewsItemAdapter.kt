package com.lab4.task6.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lab4.task6.NewsDiffItemCallback
import com.lab4.task6.databinding.NewsListItemBinding
import com.lab4.task6.models.News
import com.lab4.task6.views.NewsListFragmentDirections

class NewsItemAdapter(private val clickListener: (currentNews: News) -> Unit) : ListAdapter<News, NewsItemAdapter.NewsItemViewHolder>(NewsDiffItemCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder = NewsItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int)
    {
        val currentNews = getItem(position)
        holder.bind(currentNews, clickListener)
    }

    class NewsItemViewHolder(private val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(news: News, clickListener: (currentNews: News) -> Unit)
        {
            binding.news = news
            binding.root.setOnClickListener { clickListener(news) }
        }

        companion object
        {
            fun inflateFrom(parent: ViewGroup): NewsItemViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewsListItemBinding.inflate(layoutInflater, parent, false)
                return NewsItemViewHolder(binding)
            }
        }
    }
}