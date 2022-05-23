package com.ankurjb.newsapp.latestnews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ankurjb.newsapp.model.Article
import com.ankurjb.newsapp.newsfragments.AbstractNewsListFragment

class LatestNewsListFragment : AbstractNewsListFragment() {

    private val viewModel: LatestNewsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.latestNewsLiveData.observe(viewLifecycleOwner) { response ->
            response?.let { updateUI(it) }
        }
    }

    override fun updateNewsDetails(article: Article) = viewModel.updateNewsDetails(article)

    companion object {
        const val TAG = "LatestNewsListFragment"
        fun build() = LatestNewsListFragment()
    }
}
