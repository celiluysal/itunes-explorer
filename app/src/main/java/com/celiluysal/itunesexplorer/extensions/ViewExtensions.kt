package com.celiluysal.itunesexplorer.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.isVisible(): Boolean = this.visibility == View.VISIBLE

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.toggleVisibility() {
    if (isVisible)
        gone()
    else
        visible()
}