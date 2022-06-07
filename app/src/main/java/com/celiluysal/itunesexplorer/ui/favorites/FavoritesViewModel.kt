package com.celiluysal.itunesexplorer.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.celiluysal.itunesexplorer.data.model.Resource
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val stateLiveDataPrivate = MutableLiveData<Resource<List<MediaItem>>>()
    val stateLiveData: LiveData<Resource<List<MediaItem>>> get() = stateLiveDataPrivate

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            stateLiveDataPrivate.value = Resource.Loading()
            repository.getAllFavoriteMediaItems().collect { list ->
                if (list.isNotEmpty())
                    stateLiveDataPrivate.value = Resource.Success(list)
            }
        }
    }

    fun getDetailAction(position: Int): NavDirections? {
        return stateLiveDataPrivate.value?.data?.get(position)?.let {
            FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(it)
        }
    }

}