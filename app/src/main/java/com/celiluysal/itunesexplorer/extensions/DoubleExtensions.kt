package com.celiluysal.itunesexplorer.extensions

import java.text.DecimalFormat
import java.util.*
import kotlin.math.absoluteValue

fun Double.toDecimalString(): String? {
    val formatter = DecimalFormat.getInstance() as DecimalFormat
    formatter.maximumFractionDigits = 2
    formatter.minimumFractionDigits = 2
    return formatter.format(this.absoluteValue)
}

fun Double.toFormattedPrice(currency: String? = null): String? {
    return currency?.let {
        this.toDecimalString().plus(Currency.getInstance(it).symbol)
    } ?: kotlin.run {
        this.toDecimalString()
    }
}