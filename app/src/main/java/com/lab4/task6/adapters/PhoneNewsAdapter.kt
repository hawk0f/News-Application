package com.lab4.task6.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lab4.task6.NewsDiffItemCallback
import com.lab4.task6.databinding.FragmentNewsListPhoneBinding
import com.lab4.task6.databinding.NewsListItemBinding
import com.lab4.task6.models.News
import com.lab4.task6.viewsPhone.NewsListPhoneFragmentDirections

class PhoneNewsAdapter() : ListAdapter<News, PhoneNewsAdapter.PhoneNewsViewHolder>(NewsDiffItemCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneNewsViewHolder
    {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhoneNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhoneNewsViewHolder, position: Int)
    {
        val currentNews = getItem(position)
        holder.bind(currentNews)
    }

    class PhoneNewsViewHolder(private val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(news: News)
        {
            binding.news = news
            binding.root.setOnClickListener {
                //val action = NewsListPhoneFragmentDirections.actionNewsListFragmentToWebViewFragment(currentNews)
                //holder.itemView.findNavController().navigate(action)
            }
        }

        companion object
        {
            fun inflateFrom(parent: ViewGroup): PhoneNewsViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewsListItemBinding.inflate(layoutInflater, parent, false)
                return PhoneNewsViewHolder(binding)
            }
        }
    }
}