package com.celiluysal.itunesexplorer.ui.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected abstract val viewModel: VM
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        loadUI()
    }

    abstract fun getViewBinding(): VB

    abstract fun loadUI()
}