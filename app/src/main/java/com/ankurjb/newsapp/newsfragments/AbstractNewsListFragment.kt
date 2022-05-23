package com.ankurjb.newsapp.newsfragments

import android.os.Bundle
import android.view.View
import com.ankurjb.newsapp.base.ViewBindingFragment
import com.ankurjb.newsapp.databinding.FragmentNewsListBinding
import com.ankurjb.newsapp.isGone
import com.ankurjb.newsapp.isVisible
import com.ankurjb.newsapp.model.Article
import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.News
import com.ankurjb.newsapp.newsfragments.adapter.NewsListAdapter
import com.ankurjb.newsapp.showMessage

abstract class AbstractNewsListFragment : ViewBindingFragment<FragmentNewsListBinding>(
    FragmentNewsListBinding::inflate
) {

    private lateinit var adapter: NewsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsListAdapter {
            updateNewsDetails(it)
        }
        binding.newsListRecyclerView.adapter = adapter
    }

    abstract fun updateNewsDetails(article: Article)

    fun updateUI(state: Either<News>) {
        when (state) {
            is Either.Success -> {
                binding.progressCircular.isGone()
                adapter.apply {
                    articles = state.response.articles
                    notifyDataSetChanged()
                }
            }
            is Either.Error -> {
                binding.progressCircular.isGone()
                requireActivity().showMessage(state.errorMessage)
            }
            is Either.Loading -> {
                binding.progressCircular.isVisible()
            }
        }
    }
}
