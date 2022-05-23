package com.ankurjb.newsapp.topnews

import android.os.Bundle
import android.view.View
import com.ankurjb.newsapp.model.Article
import com.ankurjb.newsapp.newsfragments.AbstractNewsDetailsFragment

class TopNewsDetailsFragment : AbstractNewsDetailsFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = getArgs(arguments).article

        setUpUI(article)
        setUpListeners(article)
    }

    companion object {
        const val TAG = "TopNewsDetailsFragment"
        private const val EXTRA_ARTICLE = "EXTRA_ARTICLE"

        data class Args(
            val article: Article
        ) {
            fun build() = TopNewsDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_ARTICLE, article)
                }
            }
        }

        fun getArgs(arguments: Bundle?) = Args(
            article = arguments?.getParcelable(EXTRA_ARTICLE) ?: Article()
        )
    }
}
