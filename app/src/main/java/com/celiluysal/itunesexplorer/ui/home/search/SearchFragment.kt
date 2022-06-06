package com.celiluysal.itunesexplorer.ui.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adroitandroid.chipcloud.ChipListener
import com.celiluysal.itunesexplorer.data.model.Resource
import com.celiluysal.itunesexplorer.data.model.enums.EntityEnum
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.data.model.responses.SearchResult
import com.celiluysal.itunesexplorer.databinding.FragmentSearchBinding
import com.celiluysal.itunesexplorer.extensions.gone
import com.celiluysal.itunesexplorer.extensions.visible
import com.celiluysal.itunesexplorer.ui.base.BaseFragment
import com.celiluysal.itunesexplorer.ui.base.listeners.RecyclerViewListener
import com.celiluysal.itunesexplorer.ui.home.search.adapter.MediaItemsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment(), RecyclerViewListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var mediaItemsAdapter: MediaItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        if (viewModel.stateLiveData.value?.data?.results?.isNotEmpty() == true)
            setupRecyclerView(viewModel.stateLiveData.value?.data?.results!!, this)

        observeViewModel()

        return binding.root
    }

    override fun loadUI() {
        if (!binding.chipCloud.isSelected)
            binding.chipCloud.setSelectedChip(viewModel.entity.index)

        binding.chipCloud.setChipListener(object : ChipListener {
            override fun chipSelected(index: Int) {
                if (viewModel.entity.index != index) {
                    viewModel.entity = EntityEnum.values()[index]
                    viewModel.resetData()
                    viewModel.search()
                }
            }

            override fun chipDeselected(index: Int) {}

        })

        binding.searchEditText.doAfterTextChanged {
            if (it.toString().length > 2 && viewModel.searchQuery != it.toString()) {
                viewModel.searchQuery = it.toString()
                viewModel.resetData()
                viewModel.search()
            }
        }

        binding.mediaItemsRecyclerview.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    if (!isLoadingShowing())
                        viewModel.search()
                }
            }
        })

    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            handleData(it)
        }
    }

    private fun handleData(status: Resource<SearchResult>) {
        when (status) {
            is Resource.Loading -> showLoading()
            is Resource.Success -> status.data?.results?.let { list ->
                dismissLoading()
                if (list.isNotEmpty())
                    bindListData(list)
                else
                    showEmptyListLayout()
            }
            is Resource.DataError -> {
                dismissLoading()
                //todo show error
            }
        }
    }

    private fun bindListData(list: List<MediaItem?>) {
        hideEmptyListLayout()
        if (viewModel.isAdapterInitialized)
            mediaItemsAdapter.notifyItemRangeInserted(
                viewModel.insertPosition,
                viewModel.newItemsCount
            )
        else
            setupRecyclerView(list, this)
    }

    private fun setupRecyclerView(list: List<MediaItem?>, listener: RecyclerViewListener) {
        viewModel.isAdapterInitialized = true
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(position: Int) {
        findNavController().navigate(viewModel.getDetailAction(position))
    }

}