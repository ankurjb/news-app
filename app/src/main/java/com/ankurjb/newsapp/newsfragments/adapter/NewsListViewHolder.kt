package com.ankurjb.newsapp.newsfragments.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ankurjb.newsapp.databinding.NewsListViewBinding
import com.ankurjb.newsapp.model.Article

class NewsListViewHolder(
    private val binding: NewsListViewBinding,
    private val articleClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { articleClickListener(adapterPosition) }
    }

    fun bind(article: Article) = with(binding) {
        title.text = article.title
    }
}
