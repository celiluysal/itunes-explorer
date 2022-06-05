package com.celiluysal.itunesexplorer.ui.home.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    val stateLiveData = MutableLiveData<Resource<SearchResult>>()
    var mediaItemList: ArrayList<MediaItem> = arrayListOf()

    var entity: EntityEnum = EntityEnum.MOVIE
    var searchQuery: String = ""

    var isAdapterInitialized: Boolean = false
    var newItemsCount = 0
    private var offset = 0

    fun search() {
        viewModelScope.launch {
            stateLiveData.value = Resource.Loading()
            repository.search(
                searchQuery ,
                context.resources.getInteger(R.integer.search_result_limit),
                0,
                entity.key
            ).collect { resource ->
                if (resource is Resource.DataError)
                    stateLiveData.value = resource

                resource.data?.results?.let {
                    mediaItemList.addAll(it)
                    newItemsCount = it.size
                    offset += newItemsCount

                    stateLiveData.value = Resource.Success(SearchResult(resource.data.resultCount, mediaItemList))
                }
            }
        }
    }

    fun getInsertPosition() = mediaItemList.size - newItemsCount

    fun getDetailAction(position: Int): NavDirections? {
        return stateLiveData.value?.data?.results?.get(position)?.let {
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(it)
        }
    }

}