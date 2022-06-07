package com.celiluysal.itunesexplorer.ui.home.favorites

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.celiluysal.itunesexplorer.databinding.FragmentFavoritesBinding
import com.celiluysal.itunesexplorer.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>() {

    override fun getViewBinding(): FragmentFavoritesBinding =
        FragmentFavoritesBinding.inflate(layoutInflater)

    override val viewModel: FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            viewModel.mediaItem = DetailFragmentArgs.fromBundle(it).mediaItem
        }
    }

    override fun setupUI() {
        with(binding) {
//            mediaItemImageview.loadImage(viewModel.mediaItem.artworkUrl100)
//            mediaItemNameTextview.text = viewModel.mediaItem.collectionName
//            mediaItemDateTextview.text = viewModel.mediaItem.releaseDate
//            mediaItemPriceTextview.text = viewModel.mediaItem.collectionPrice.toString()
//            mediaItemDetailTextview.text = viewModel.mediaItem.longDescription
        }

    }

    override fun observeViewModel() {

    }

}