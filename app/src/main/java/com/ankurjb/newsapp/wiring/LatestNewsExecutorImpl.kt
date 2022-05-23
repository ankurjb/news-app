package com.ankurjb.newsapp.wiring

import android.content.Intent
import com.ankurjb.latestnews.LatestNewsActivity
import com.ankurjb.latestnews.LatestNewsExecutor
import com.ankurjb.topnews.TopNewsActivity
import javax.inject.Inject

class LatestNewsExecutorImpl @Inject constructor() : LatestNewsExecutor {
    override fun getTopNewsIntent(context: LatestNewsActivity): Intent {
        return Intent(context, TopNewsActivity::class.java)
    }
}
