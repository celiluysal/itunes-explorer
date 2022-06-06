package com.celiluysal.itunesexplorer.extensions

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.celiluysal.itunesexplorer.R

fun TextView.setPriceText(context: Context, price: Double?, currency: String?) {
    if (price == 0.0 || price == null) {
        this.text = context.resources.getString(R.string.free)
        this.background.setTint(
            ContextCompat.getColor(
                context,
                R.color.free
            )
        )
    } else
        this.text = price.toFormattedPrice(currency)
}