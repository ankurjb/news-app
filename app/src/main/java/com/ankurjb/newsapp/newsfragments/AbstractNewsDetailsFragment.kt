package com.ankurjb.newsapp.newsfragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.ankurjb.newsapp.base.ViewBindingFragment
import com.ankurjb.newsapp.databinding.FragmentNewsDetailsBinding
import com.ankurjb.newsapp.model.Article
import com.squareup.picasso.Picasso


class AbstractNewsDetailsFragment : ViewBindingFragment<FragmentNewsDetailsBinding>(
    FragmentNewsDetailsBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = getArgs(arguments).article

        setUpUI(article)
        setUpListeners(article)
    }

    private fun setUpListeners(article: Article) {
        binding.image.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            )
        }
    }

    private fun setUpUI(article: Article) = with(binding) {
        Picasso.get().load(article.urlToImage).into(image)
        title.text = article.title
        description.text = article.description
        author.text = article.author
    }

    companion object {
        const val TAG = "AbstractNewsDetailsFragment"
        private const val EXTRA_ARTICLE = "EXTRA_ARTICLE"

        data class Args(
            val article: Article
        ) {
            fun build() = AbstractNewsDetailsFragment().apply {
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
