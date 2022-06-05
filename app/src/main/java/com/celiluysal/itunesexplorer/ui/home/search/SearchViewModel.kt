package com.celiluysal.itunesexplorer.ui.home.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.FieldPosition
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    val mediaItemsLiveData = MutableLiveData<List<MediaItem?>>()

    fun search() {
        viewModelScope.launch {
            repository.search("r", 20, 0, "movie").collect { resource ->
                resource.data?.results?.let {
                    mediaItemsLiveData.value = it
                }

            }
        }
    }

    fun getDetailAction(position: Int): NavDirections? {
        return mediaItemsLiveData.value?.get(position)?.let {
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(it)
        }
    }

}