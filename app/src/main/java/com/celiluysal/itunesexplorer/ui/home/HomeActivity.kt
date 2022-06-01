package com.celiluysal.itunesexplorer.ui.home

import androidx.activity.viewModels
import com.celiluysal.itunesexplorer.databinding.ActivityHomeBinding
import com.celiluysal.itunesexplorer.ui.base.BaseActivity

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {

    override val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun loadUI() {

    }

}