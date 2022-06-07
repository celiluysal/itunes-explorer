package com.celiluysal.itunesexplorer.ui.home.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celiluysal.itunesexplorer.data.model.Resource
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.data.model.responses.SearchResult
import com.celiluysal.itunesexplorer.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    lateinit var mediaItem: MediaItem

    private val favoriteLiveDataPrivate = MutableLiveData<Boolean>()
    val favoriteLiveData: LiveData<Boolean> get() = favoriteLiveDataPrivate

    fun checkFavorite() {
        viewModelScope.launch {
            favoriteLiveDataPrivate.value = repository.isMediaItemFavorite(mediaItem)
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            if (favoriteLiveDataPrivate.value == true)
                repository.deleteFavoriteMediaItem(mediaItem)
            else
                repository.addFavoriteMediaItem(mediaItem)
        }
    }

}