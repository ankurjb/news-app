package com.ankurjb.topnews

import android.content.Intent

interface TopNewsExecutor {

    fun getLatestNewsIntent(context: TopNewsActivity): Intent
}