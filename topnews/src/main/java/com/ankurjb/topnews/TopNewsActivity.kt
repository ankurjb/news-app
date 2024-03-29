package com.ankurjb.topnews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.ankurjb.core.base.ViewBindingActivity
import com.ankurjb.core.model.Article
import com.ankurjb.topnews.databinding.ActivityTopNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopNewsActivity : ViewBindingActivity<ActivityTopNewsBinding>(
    ActivityTopNewsBinding::inflate
) {

    private val viewModel: TopNewsViewModel by viewModels()

    @Inject
    lateinit var topNewsExecutor: TopNewsExecutor

    private lateinit var args: Args

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = from(intent)
        setUpBottomBar()

        addNewsListFragment()
        Log.d("MyText", "onCreate: 1")
        viewModel.update()
        Log.d("MyText", "onCreate: 2")
        viewModel.getTopNews()
        viewModel.newsDetailsLiveData.observe(this) {
            addNewsDetailsFragment(it)
        }
    }

    private fun setUpBottomBar() = with(binding) {
        topNews.isEnabled = false
        latestNews.setOnClickListener {
            startActivity(topNewsExecutor.getLatestNewsIntent(this@TopNewsActivity))
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

    companion object {
        private const val KEY = "KEY"

        fun from(intent: Intent) = Args(
            name = intent.getStringExtra(KEY) ?: ""
        )

        data class Args(val name: String) {
            fun build(context: Context) = Intent(context, TopNewsActivity::class.java).apply {
                putExtra(KEY, name)
            }
        }
    }
}
