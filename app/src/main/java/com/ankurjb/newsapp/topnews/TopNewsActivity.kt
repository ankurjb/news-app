package com.ankurjb.newsapp.topnews

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.ankurjb.newsapp.NewsClient
import com.ankurjb.newsapp.RetrofitInstance
import com.ankurjb.newsapp.TopNewsRepositoryImpl
import com.ankurjb.newsapp.base.ViewBindingActivity
import com.ankurjb.newsapp.databinding.ActivityTopNewsBinding
import com.ankurjb.newsapp.latestnews.LatestNewsActivity
import com.ankurjb.newsapp.model.Article
import com.ankurjb.newsapp.newsfragments.AbstractNewsDetailsFragment
import com.ankurjb.newsapp.newsfragments.AbstractNewsListFragment

class TopNewsActivity : ViewBindingActivity<ActivityTopNewsBinding>(
    ActivityTopNewsBinding::inflate
) {

    private val viewModel: TopNewsViewModel by viewModelsFactory {
        val newsClient = NewsClient(RetrofitInstance.newsApi)
        val repository = TopNewsRepositoryImpl(newsClient)
        TopNewsViewModel(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomBar.topNews.isEnabled = false
        binding.bottomBar.latestNews.setOnClickListener {
            startActivity(Intent(this, LatestNewsActivity::class.java))
        }

        addNewsListFragment()

        viewModel.getTopNews()
        viewModel.newsDetailsLiveData.observe(this) {
            addNewsDetailsFragment(it)
        }
    }

    private fun addNewsListFragment() {
        supportFragmentManager.findFragmentByTag(
            AbstractNewsListFragment.TAG
        ) ?: supportFragmentManager.commit {
            add(
                binding.container.id,
                AbstractNewsListFragment.build(),
                AbstractNewsListFragment.TAG
            )
        }
    }

    private fun addNewsDetailsFragment(article: Article) {
        supportFragmentManager.findFragmentByTag(
            AbstractNewsDetailsFragment.TAG
        ) ?: supportFragmentManager.commit {
            addToBackStack("nju")
            add(
                binding.container.id,
                AbstractNewsDetailsFragment.Companion.Args(
                    article = article
                ).build(),
                AbstractNewsDetailsFragment.TAG
            )
        }
    }
}
