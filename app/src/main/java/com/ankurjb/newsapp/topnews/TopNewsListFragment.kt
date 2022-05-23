package com.ankurjb.newsapp.topnews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ankurjb.newsapp.model.Article
import com.ankurjb.newsapp.newsfragments.AbstractNewsListFragment

class TopNewsListFragment : AbstractNewsListFragment() {

    private val viewModel: TopNewsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
    }

    private fun addObservers() = with(viewModel) {
        topNewsLiveData.observe(viewLifecycleOwner) { response ->
            response?.let { updateUI(it) }
        }
    }

    override fun updateNewsDetails(article: Article) = viewModel.updateNewsDetails(article)

    companion object {
        const val TAG = "TopNewsListFragment"
        fun build() = TopNewsListFragment()
    }
}
