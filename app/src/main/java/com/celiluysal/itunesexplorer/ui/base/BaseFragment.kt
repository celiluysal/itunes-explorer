package com.celiluysal.itunesexplorer.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {
    protected lateinit var binding: VB
    protected abstract val viewModel: VM

    abstract fun getViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
    }

    abstract fun setupUI()
    abstract fun observeViewModel()

    fun showLoading() {
        (activity as BaseActivity<*, *>?)?.showLoading()
    }

    fun dismissLoading() {
        (activity as BaseActivity<*, *>?)?.dismissLoading()
    }

    fun isLoadingShowing(): Boolean = (activity as BaseActivity<*, *>?)?.isLoadingShowing() == true

}