package com.ankurjb.newsapp.newsfragments

import android.content.Intent
import android.net.Uri
import com.ankurjb.newsapp.base.ViewBindingFragment
import com.ankurjb.newsapp.databinding.FragmentNewsDetailsBinding
import com.ankurjb.newsapp.model.Article
import com.squareup.picasso.Picasso


open class AbstractNewsDetailsFragment : ViewBindingFragment<FragmentNewsDetailsBinding>(
    FragmentNewsDetailsBinding::inflate
) {

    fun setUpListeners(article: Article) {
        binding.image.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            )
        }
    }

    fun setUpUI(article: Article) = with(binding) {
        Picasso.get().load(article.urlToImage).into(image)
        title.text = article.title
        description.text = article.description
        author.text = article.author
    }
}
