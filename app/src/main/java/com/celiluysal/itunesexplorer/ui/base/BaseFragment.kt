package com.celiluysal.itunesexplorer.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUI()
    }

    abstract fun loadUI()

    fun showLoading() {
        (activity as BaseActivity?)?.showLoading()
    }

    fun dismissLoading() {
        (activity as BaseActivity?)?.dismissLoading()
    }

    fun isLoadingShowing(): Boolean = (activity as BaseActivity?)?.isLoadingShowing() == true

}