package com.celiluysal.itunesexplorer.ui.splash

import androidx.lifecycle.ViewModel
import com.celiluysal.itunesexplorer.utils.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val networkUtil: NetworkUtil) : ViewModel() {

    val hasConnection: Boolean = networkUtil.isNetworkAvailable()

}