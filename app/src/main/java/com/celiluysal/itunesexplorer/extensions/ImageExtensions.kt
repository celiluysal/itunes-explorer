package com.celiluysal.itunesexplorer.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun ImageView.loadImage(resId: Int?) {
    Glide.with(context)
        .load(resId)
        .into(this)
}