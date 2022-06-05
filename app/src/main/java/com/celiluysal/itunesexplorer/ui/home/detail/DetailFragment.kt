package com.celiluysal.itunesexplorer.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.celiluysal.itunesexplorer.databinding.FragmentDetailBinding
import com.celiluysal.itunesexplorer.extensions.loadImage
import com.celiluysal.itunesexplorer.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: BaseFragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.mediaItem = DetailFragmentArgs.fromBundle(it).mediaItem
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun loadUI() {
        with(binding) {
            mediaItemImageview.loadImage(viewModel.mediaItem.artworkUrl100)
            mediaItemNameTextview.text = viewModel.mediaItem.collectionName
            mediaItemDateTextview.text = viewModel.mediaItem.releaseDate
            mediaItemPriceTextview.text = viewModel.mediaItem.collectionPrice.toString()
            mediaItemDetailTextview.text = viewModel.mediaItem.longDescription
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}