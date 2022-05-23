package com.ankurjb.newsapp.newsfragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ankurjb.newsapp.databinding.NewsListViewBinding
import com.ankurjb.newsapp.model.Article

class NewsListAdapter(
    var articles: List<Article> = emptyList(),
    private val articleClickListener: (Article) -> Unit
) : RecyclerView.Adapter<NewsListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = NewsListViewHolder(
        NewsListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) {
        articleClickListener(articles[it])
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() = articles.size
}
