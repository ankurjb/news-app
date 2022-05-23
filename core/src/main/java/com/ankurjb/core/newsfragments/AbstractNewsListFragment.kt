package com.ankurjb.core.newsfragments

import android.os.Bundle
import android.view.View
import com.ankurjb.core.base.ViewBindingFragment
import com.ankurjb.core.databinding.FragmentNewsListBinding
import com.ankurjb.core.isGone
import com.ankurjb.core.isVisible
import com.ankurjb.core.model.Article
import com.ankurjb.core.model.Either
import com.ankurjb.core.model.News
import com.ankurjb.core.newsfragments.adapter.NewsListAdapter
import com.ankurjb.core.showMessage

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
