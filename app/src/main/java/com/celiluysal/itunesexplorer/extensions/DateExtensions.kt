package com.celiluysal.itunesexplorer.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
const val DATE_DOT_TEXT_FORMAT = "dd.MM.yyyy"

fun String.toDate(format: String): Date? = try {
    SimpleDateFormat(format, Locale.getDefault()).parse(this)
} catch (e: ParseException) {
    null
}

fun Date.toString(format: String): String? =
    SimpleDateFormat(format, Locale.getDefault()).format(this)