package com.ankurjb.latestnews

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.ankurjb.core.base.ViewBindingActivity
import com.ankurjb.core.model.Article
import com.ankurjb.latestnews.databinding.ActivityLatestNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LatestNewsActivity : ViewBindingActivity<ActivityLatestNewsBinding>(
    ActivityLatestNewsBinding::inflate
) {

    private val viewModel: LatestNewsViewModel by viewModels()

    @Inject
    lateinit var latestNewsExecutor: LatestNewsExecutor

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
            startActivity(latestNewsExecutor.getTopNewsIntent(this@LatestNewsActivity))
        }
    }
}
