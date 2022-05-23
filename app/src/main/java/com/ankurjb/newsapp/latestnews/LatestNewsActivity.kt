package com.ankurjb.newsapp.latestnews

import android.content.Intent
import android.os.Bundle
import com.ankurjb.newsapp.topnews.TopNewsActivity
import com.ankurjb.newsapp.base.ViewBindingActivity
import com.ankurjb.newsapp.databinding.ActivityLatestNewsBinding

class LatestNewsActivity : ViewBindingActivity<ActivityLatestNewsBinding>(
    ActivityLatestNewsBinding::inflate
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bottomBar.latestNews.isEnabled = false
        binding.bottomBar.topNews.setOnClickListener {
            startActivity(Intent(this, TopNewsActivity::class.java))
        }

    }
}
