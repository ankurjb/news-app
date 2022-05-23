package com.ankurjb.newsapp.latestnews

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.commit
import com.ankurjb.newsapp.NewsClient
import com.ankurjb.newsapp.RetrofitInstance
import com.ankurjb.newsapp.base.ViewBindingActivity
import com.ankurjb.newsapp.databinding.ActivityLatestNewsBinding
import com.ankurjb.newsapp.latestnews.network.LatestNewsRepositoryImpl
import com.ankurjb.newsapp.model.Article
import com.ankurjb.newsapp.topnews.TopNewsActivity
import com.ankurjb.newsapp.viewModelsFactory

class LatestNewsActivity : ViewBindingActivity<ActivityLatestNewsBinding>(
    ActivityLatestNewsBinding::inflate
) {

    private val viewModel: LatestNewsViewModel by viewModelsFactory {
        val newsClient = NewsClient(RetrofitInstance.newsApi)
        val repository = LatestNewsRepositoryImpl(newsClient)
        LatestNewsViewModel(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBottomBar()

        addNewsListFragment()

        viewModel.getLatestNews()
        viewModel.newsDetailsLiveData.observe(this) {
            addNewsDetailsFragment(it)
        }

    }

    private fun addNewsListFragment() {
        supportFragmentManager.findFragmentByTag(
            LatestNewsListFragment.TAG
        ) ?: supportFragmentManager.commit {
            add(
                binding.container.id,
                LatestNewsListFragment.build(),
                LatestNewsListFragment.TAG
            )
        }
    }

    private fun addNewsDetailsFragment(article: Article) {
        supportFragmentManager.findFragmentByTag(
            LatestNewsDetailsFragment.TAG
        ) ?: supportFragmentManager.commit {
            addToBackStack("TAG")
            add(
                binding.container.id,
                LatestNewsDetailsFragment.Companion.Args(
                    article = article
                ).build(),
                LatestNewsDetailsFragment.TAG
            )
        }
    }

    private fun setUpBottomBar() = with(binding.bottomBar) {
        latestNews.isEnabled = false
        topNews.setOnClickListener {
            startActivity(Intent(this@LatestNewsActivity, TopNewsActivity::class.java))
        }
    }
}
