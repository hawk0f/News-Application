package com.lab4.task6

import androidx.recyclerview.widget.DiffUtil
import com.lab4.task6.models.News

class NewsDiffItemCallback : DiffUtil.ItemCallback<News>()
{
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean = (oldItem.link == newItem.link)

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean = (oldItem == newItem)
}