package com.ankurjb.newsapp.topnews

import android.content.Intent
import android.os.Bundle
import com.ankurjb.newsapp.NewsClient
import com.ankurjb.newsapp.RetrofitInstance
import com.ankurjb.newsapp.TopNewsRepositoryImpl
import com.ankurjb.newsapp.base.ViewBindingActivity
import com.ankurjb.newsapp.databinding.ActivityTopNewsBinding
import com.ankurjb.newsapp.latestnews.LatestNewsActivity
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
    }

    private fun addNewsListFragment() {
        supportFragmentManager.findFragmentByTag(
            AbstractNewsListFragment.TAG
        ) ?: supportFragmentManager
            .beginTransaction()
            .replace(
                binding.container.id,
                AbstractNewsListFragment.build(),
                AbstractNewsListFragment.TAG
            ).commit()
    }
}
