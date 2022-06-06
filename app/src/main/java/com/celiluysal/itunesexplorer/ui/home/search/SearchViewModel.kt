package com.celiluysal.itunesexplorer.ui.home.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.celiluysal.itunesexplorer.R
import com.celiluysal.itunesexplorer.data.model.Resource
import com.celiluysal.itunesexplorer.data.model.enums.EntityEnum
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.data.model.responses.SearchResult
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

    private val stateLiveDataPrivate = MutableLiveData<Resource<SearchResult>>()
    val stateLiveData: LiveData<Resource<SearchResult>> get() = stateLiveDataPrivate

    private var mediaItemList: ArrayList<MediaItem> = arrayListOf()

    var entity: EntityEnum = EntityEnum.MOVIE
    var searchQuery: String = ""
    private var offset = 0

    var isAdapterInitialized: Boolean = false
    val insertPosition: Int get() = mediaItemList.size - newItemsCount
    var newItemsCount = 0
        private set

    fun search() {
        viewModelScope.launch {
            stateLiveDataPrivate.value = Resource.Loading()
            repository.search(
                searchQuery,
                context.resources.getInteger(R.integer.search_result_limit),
                offset,
                entity.key
            ).collect { resource ->
                if (resource is Resource.DataError)
                    stateLiveDataPrivate.value = resource

                resource.data?.results?.let {
                    mediaItemList.addAll(it)
                    newItemsCount = it.size
                    offset += newItemsCount + 1

                    stateLiveDataPrivate.value =
                        Resource.Success(SearchResult(resource.data.resultCount, mediaItemList))
                }
            }
        }
    }

    fun resetData() {
        isAdapterInitialized = false
        newItemsCount = 0
        offset = 0
        mediaItemList.clear()
    }

    fun getDetailAction(position: Int): NavDirections {
        return mediaItemList[position].let {
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(it)
        }
    }

}