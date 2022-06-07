package com.celiluysal.itunesexplorer.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.celiluysal.itunesexplorer.R

fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.mipmap.ic_launcher_round)
        .into(this)
}

fun ImageView.loadImage(resId: Int?) {
    Glide.with(context)
        .load(resId)
        .into(this)
}

fun ImageView.setFavoriteIcon(favorite: Boolean) {
    (if (favorite) R.drawable.ic_round_favorite_24 else R.drawable.ic_round_favorite_border_24).let {
        this.loadImage(it)
    }
}