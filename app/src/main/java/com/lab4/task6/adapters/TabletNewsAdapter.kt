package com.lab4.task6.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.lab4.task6.models.News
import com.lab4.task6.databinding.NewsListItemBinding
import com.lab4.task6.viewModels.MainActivityViewModel

class TabletNewsAdapter(private val viewModel: MainActivityViewModel, private val onNewsClickListener: OnNewsClickListener) : RecyclerView.Adapter<TabletNewsAdapter.TabletNewsViewHolder>()
{
    private lateinit var layout: LinearLayout

    private var newsList: MutableList<News> = viewModel.newsList.value!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabletNewsViewHolder
    {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        layout = parent.parent.parent as LinearLayout

        return TabletNewsViewHolder(binding, onNewsClickListener, newsList)
    }

    override fun onBindViewHolder(holder: TabletNewsViewHolder, position: Int)
    {
        val currentNews = newsList[position]
        holder.bind(currentNews)
    }

    override fun getItemCount(): Int
    {
        return newsList.size
    }

    fun updateNewsList(newsList: MutableList<News>)
    {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    fun updateNews(news: News, position: Int)
    {
        newsList[position] = news
        notifyItemChanged(position)
    }

    class TabletNewsViewHolder(binding: NewsListItemBinding, private val onNewsClickListener: OnNewsClickListener, private val newsList: MutableList<News>) : RecyclerView.ViewHolder(binding.root)
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

            binding.root.setOnClickListener {
                onNewsClickListener.onNewsClick(news, newsList)
            }
        }
    }

    interface OnNewsClickListener {
        fun onNewsClick(news: News, newsList: MutableList<News>)
    }
}