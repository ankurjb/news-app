package com.ankurjb.newsapp.topnews

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.ankurjb.newsapp.base.ViewBindingActivity
import com.ankurjb.newsapp.databinding.ActivityTopNewsBinding
import com.ankurjb.newsapp.latestnews.LatestNewsActivity
import com.ankurjb.newsapp.model.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopNewsActivity : ViewBindingActivity<ActivityTopNewsBinding>(
    ActivityTopNewsBinding::inflate
) {

    private val viewModel: TopNewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpBottomBar()

        addNewsListFragment()

        viewModel.getTopNews()
        viewModel.newsDetailsLiveData.observe(this) {
            addNewsDetailsFragment(it)
        }
    }

    private fun setUpBottomBar() = with(binding.bottomBar) {
        topNews.isEnabled = false
        latestNews.setOnClickListener {
            startActivity(Intent(this@TopNewsActivity, LatestNewsActivity::class.java))
        }
    }

    private fun addNewsListFragment() {
        supportFragmentManager.findFragmentByTag(
            TopNewsListFragment.TAG
        ) ?: supportFragmentManager.commit {
            add(
                binding.container.id,
                TopNewsListFragment.build(),
                TopNewsListFragment.TAG
            )
        }
    }

    private fun addNewsDetailsFragment(article: Article) {
        supportFragmentManager.findFragmentByTag(
            TopNewsDetailsFragment.TAG
        ) ?: supportFragmentManager.commit {
            addToBackStack("TAG")
            add(
                binding.container.id,
                TopNewsDetailsFragment.Companion.Args(
                    article = article
                ).build(),
                TopNewsDetailsFragment.TAG
            )
        }
    }
}
