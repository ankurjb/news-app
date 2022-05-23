package com.ankurjb.core

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible

fun Context.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.isGone() {
    this.isGone = true
}

fun View.isVisible() {
    this.isVisible = true
}
