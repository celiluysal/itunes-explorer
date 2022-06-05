package com.celiluysal.itunesexplorer.ui.home.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.celiluysal.itunesexplorer.data.model.enums.EntityEnum
import com.celiluysal.itunesexplorer.databinding.FragmentSearchBinding
import com.celiluysal.itunesexplorer.ui.base.BaseFragment
import com.celiluysal.itunesexplorer.ui.base.listeners.RecyclerViewListener
import com.celiluysal.itunesexplorer.ui.home.search.adapter.MediaItemsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment(), RecyclerViewListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.search("a")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun loadUI() {
        binding.chipCloud.setSelectedChip(EntityEnum.MOVIE.index)

        binding.searchEditText.doAfterTextChanged {
            if (it.toString().length > 2) {
                viewModel.search(it.toString())
            }
        }

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