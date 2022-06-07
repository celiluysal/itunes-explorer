package com.celiluysal.itunesexplorer.ui.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.celiluysal.itunesexplorer.ui.custom.LoadingDialog

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM

    abstract fun getViewBinding(): VB

    private var loadingDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        loadingDialog = LoadingDialog(this).createLoading()
        setupUI()
    }

    abstract fun setupUI()

    fun showLoading() {
        loadingDialog?.show()
    }

    fun dismissLoading() {
        loadingDialog?.dismiss()
    }

    fun isLoadingShowing(): Boolean = loadingDialog?.isShowing == true
}