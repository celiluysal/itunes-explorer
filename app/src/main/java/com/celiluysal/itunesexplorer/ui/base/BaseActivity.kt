package com.celiluysal.itunesexplorer.ui.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.celiluysal.itunesexplorer.ui.custom.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseActivity : AppCompatActivity() {

    private var loadingDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = LoadingDialog(this).createLoading()
        loadUI()
    }

    abstract fun loadUI()

    fun showLoading() {
        loadingDialog?.show()
    }

    fun dismissLoading() {
        loadingDialog?.dismiss()
    }

    fun isLoadingShowing(): Boolean = loadingDialog?.isShowing == true
}