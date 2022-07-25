package com.ankurjb.latestnews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.ankurjb.core.TAG
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

    lateinit var args: Args

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBottomBar()
        args = from(intent)

        Log.d(TAG, "onCreate: $args")

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

    companion object {
        private const val KEY = "KEY"

        private fun from(intent: Intent) = Args(
            name = intent.getStringExtra(KEY) ?: ""
        )

        data class Args(val name: String) {
            fun build(context: Context) = Intent(context, LatestNewsActivity::class.java).apply {
                putExtra(KEY, name)
            }
        }
    }
}
