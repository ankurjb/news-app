package com.ankurjb.latestnews

import android.content.Intent

interface LatestNewsExecutor {

    fun getTopNewsIntent(context: LatestNewsActivity): Intent
}