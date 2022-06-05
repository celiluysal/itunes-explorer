package com.celiluysal.itunesexplorer.ui.home.detail

import androidx.lifecycle.ViewModel
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(): ViewModel() {
    lateinit var mediaItem: MediaItem

}