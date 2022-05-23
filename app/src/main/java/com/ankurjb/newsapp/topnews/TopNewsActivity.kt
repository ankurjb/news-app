package com.ankurjb.newsapp.topnews

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.ankurjb.newsapp.base.ViewBindingActivity
import com.ankurjb.newsapp.databinding.ActivityTopNewsBinding
import com.ankurjb.newsapp.latestnews.LatestNewsActivity
import com.ankurjb.newsapp.latestnews.LatestNewsViewModel

class TopNewsActivity : ViewBindingActivity<ActivityTopNewsBinding>(
    ActivityTopNewsBinding::inflate
) {

    val viewModel: TopNewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomBar.topNews.isEnabled = false
        binding.bottomBar.latestNews.setOnClickListener {
            startActivity(Intent(this, LatestNewsActivity::class.java))
        }

        viewModel.getTopNews()
    }
}
