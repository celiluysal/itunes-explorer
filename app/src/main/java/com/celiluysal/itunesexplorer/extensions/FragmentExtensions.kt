package com.celiluysal.itunesexplorer.extensions

import androidx.fragment.app.Fragment

fun Fragment.showKeyboard() {
    view?.let { activity?.showKeyboard(it) }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}