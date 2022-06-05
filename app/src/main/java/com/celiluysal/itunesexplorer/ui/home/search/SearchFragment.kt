package com.celiluysal.itunesexplorer.ui.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.celiluysal.itunesexplorer.databinding.FragmentSearchBinding
import com.celiluysal.itunesexplorer.ui.base.BaseFragment
import com.celiluysal.itunesexplorer.ui.base.listeners.RecyclerViewListener
import com.celiluysal.itunesexplorer.ui.home.search.adapter.MediaItemsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment: BaseFragment(), RecyclerViewListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.search()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun loadUI() {
        viewModel.mediaItemsLiveData.observe(viewLifecycleOwner) {
            binding.mediaItemsRecyclerview.adapter = MediaItemsAdapter(it, this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(position: Int) {
        viewModel.getDetailAction(position)?.let {
            findNavController().navigate(it)
        }
    }

}