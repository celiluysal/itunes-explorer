package com.celiluysal.itunesexplorer.extensions

import android.app.Activity
import android.view.View

fun Activity.showKeyboard() {
    showKeyboard(currentFocus ?: View(this))
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}
