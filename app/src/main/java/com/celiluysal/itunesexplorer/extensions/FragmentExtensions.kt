package com.celiluysal.itunesexplorer.extensions

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.showKeyboard() {
    view?.let { activity?.showKeyboard(it) }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.hasPermission(permission: String): Boolean = ContextCompat.checkSelfPermission(
    requireContext(),
    permission
) == PackageManager.PERMISSION_GRANTED