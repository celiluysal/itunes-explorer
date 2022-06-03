package com.celiluysal.itunesexplorer.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val mediaItemsLiveData = MutableLiveData<List<MediaItem>?>()

    fun search() {
        viewModelScope.launch {
            val searchResult = repository.search("rec", 5, 0, "movie")
            when (searchResult.isSuccessful) {
                true -> {
                    searchResult.body()?.results?.let {
                        mediaItemsLiveData.postValue(it)
                    }
                }
                else -> {}
            }
        }

    }
}