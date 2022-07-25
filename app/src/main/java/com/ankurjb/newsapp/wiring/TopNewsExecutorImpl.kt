package com.ankurjb.newsapp.wiring

import android.content.Intent
import com.ankurjb.latestnews.LatestNewsActivity
import com.ankurjb.topnews.TopNewsActivity
import com.ankurjb.topnews.TopNewsExecutor
import javax.inject.Inject

class TopNewsExecutorImpl @Inject constructor() : TopNewsExecutor {
    override fun getLatestNewsIntent(context: TopNewsActivity): Intent {
        return LatestNewsActivity.Companion.Args("TopNews").build(context)
    }
}
