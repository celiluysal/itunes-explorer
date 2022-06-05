package com.celiluysal.itunesexplorer.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.celiluysal.itunesexplorer.R
import com.celiluysal.itunesexplorer.databinding.ActivityHomeBinding
import com.celiluysal.itunesexplorer.ui.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

//        // Setup the bottom navigation view with navController
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        bottomNavigationView.setupWithNavController(navController)
//
//        // Setup the ActionBar with navController and 3 top level destinations
//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.titleScreen, R.id.leaderboard,  R.id.register)
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun loadUI() {

    }

}