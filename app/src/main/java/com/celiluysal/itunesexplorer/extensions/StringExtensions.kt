package com.celiluysal.itunesexplorer.extensions

import android.text.Spanned
import androidx.core.text.HtmlCompat

fun String.toHtmlSpanned(): Spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT)
