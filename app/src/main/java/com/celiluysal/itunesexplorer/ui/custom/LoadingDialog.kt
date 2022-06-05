package com.celiluysal.itunesexplorer.ui.custom

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import com.celiluysal.itunesexplorer.R

class LoadingDialog(private val activity: Activity) {
    fun createLoading(): AlertDialog {
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.layout_progress)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}