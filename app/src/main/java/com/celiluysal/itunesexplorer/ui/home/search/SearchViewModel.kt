package com.celiluysal.itunesexplorer.ui.home.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.celiluysal.itunesexplorer.R
import com.celiluysal.itunesexplorer.data.model.enums.EntityEnum
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {
    private val context = getApplication<Application>()
    val mediaItemsLiveData = MutableLiveData<List<MediaItem?>>()

    var entity: EntityEnum = EntityEnum.MOVIE
    var searchQuery: String = "a"

    fun search() {
        viewModelScope.launch {
            repository.search(
                searchQuery ,
                20,
                context.resources.getInteger(R.integer.search_result_limit),
                entity.key
            ).collect { resource ->
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