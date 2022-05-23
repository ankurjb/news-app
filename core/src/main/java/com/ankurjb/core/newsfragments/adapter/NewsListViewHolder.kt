package com.ankurjb.core.newsfragments.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ankurjb.core.databinding.NewsListViewBinding
import com.ankurjb.core.model.Article

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
