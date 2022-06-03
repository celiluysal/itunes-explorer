package com.celiluysal.itunesexplorer.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.celiluysal.itunesexplorer.databinding.ActivityHomeBinding
import com.celiluysal.itunesexplorer.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun loadUI() {
        viewModel.search()
    }

}