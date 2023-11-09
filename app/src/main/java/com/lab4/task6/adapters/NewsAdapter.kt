package com.lab4.task6.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lab4.task6.databinding.NewsListItemBinding
import com.lab4.task6.models.News
import com.lab4.task6.viewModels.NewsListViewModel
import com.lab4.task6.views.NewsListFragmentDirections

class NewsAdapter(private val viewModel: NewsListViewModel) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder
    {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int
    {
        return viewModel.newsList.value!!.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int)
    {
        val currentNews = viewModel.newsList.value!![position]
        holder.bind(currentNews)

        holder.itemView.setOnClickListener {
            val action = NewsListFragmentDirections.actionNewsListFragmentToWebViewFragment(currentNews)
            holder.itemView.findNavController().navigate(action)
        }
    }

    class NewsViewHolder(binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        private val binding: NewsListItemBinding

        init
        {
            this.binding = binding
        }

        fun bind(news: News)
        {
            binding.news = news
            binding.executePendingBindings()
        }
    }
}