package com.celiluysal.itunesexplorer.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.celiluysal.itunesexplorer.data.model.Resource
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.data.model.responses.SearchResult
import com.celiluysal.itunesexplorer.databinding.FragmentFavoritesBinding
import com.celiluysal.itunesexplorer.extensions.gone
import com.celiluysal.itunesexplorer.extensions.visible
import com.celiluysal.itunesexplorer.ui.adapters.MediaItemsAdapter
import com.celiluysal.itunesexplorer.ui.base.BaseFragment
import com.celiluysal.itunesexplorer.ui.base.listeners.RecyclerViewListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(),
    RecyclerViewListener {

    override fun getViewBinding(): FragmentFavoritesBinding =
        FragmentFavoritesBinding.inflate(layoutInflater)

    override val viewModel: FavoritesViewModel by viewModels()

    private lateinit var mediaItemsAdapter: MediaItemsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.stateLiveData.value?.data?.isNotEmpty() == true)
            setupRecyclerView(viewModel.stateLiveData.value?.data!!, this)
    }

    override fun setupUI() {

    }

    override fun observeViewModel() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            handleData(it)
        }
    }

    private fun handleData(status: Resource<List<MediaItem>>) {
        when (status) {
            is Resource.Loading -> showLoading()
            is Resource.Success -> status.data?.let { list ->
                dismissLoading()
                if (list.isNotEmpty())
                    setupRecyclerView(list, this)
                else
                    showEmptyListLayout()
            }
            is Resource.DataError -> {
                dismissLoading()
                //todo show error
            }
        }
    }

    private fun setupRecyclerView(list: List<MediaItem?>, listener: RecyclerViewListener) {
        hideEmptyListLayout()
        mediaItemsAdapter = MediaItemsAdapter(list, listener)
        binding.mediaItemsRecyclerview.run {
            adapter = mediaItemsAdapter
            setHasFixedSize(true)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }

    private fun hideEmptyListLayout() {
        binding.emptyListLayout.gone()
        binding.mediaItemsRecyclerview.visible()
    }

    private fun showEmptyListLayout() {
        binding.mediaItemsRecyclerview.gone()
        binding.emptyListLayout.visible()
    }

    override fun onItemClicked(position: Int) {
        viewModel.getDetailAction(position)?.let { findNavController().navigate(it) }
    }

}