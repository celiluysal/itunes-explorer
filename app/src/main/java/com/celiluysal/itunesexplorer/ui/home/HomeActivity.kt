package com.celiluysal.itunesexplorer.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.celiluysal.itunesexplorer.R
import com.celiluysal.itunesexplorer.databinding.ActivityHomeBinding
import com.celiluysal.itunesexplorer.extensions.gone
import com.celiluysal.itunesexplorer.extensions.visible
import com.celiluysal.itunesexplorer.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment).navController

        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.detailFragment)
                binding.bottomNavigation.gone()
            else
                binding.bottomNavigation.visible()
        }
    }

    override fun loadUI() {

    }

}