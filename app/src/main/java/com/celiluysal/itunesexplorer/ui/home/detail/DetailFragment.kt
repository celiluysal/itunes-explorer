package com.celiluysal.itunesexplorer.ui.home.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.celiluysal.itunesexplorer.R
import com.celiluysal.itunesexplorer.databinding.FragmentDetailBinding
import com.celiluysal.itunesexplorer.extensions.*
import com.celiluysal.itunesexplorer.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override fun getViewBinding(): FragmentDetailBinding = FragmentDetailBinding.inflate(layoutInflater)

    override val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.mediaItem = DetailFragmentArgs.fromBundle(it).mediaItem
            viewModel.checkFavorite()
        }
    }

    override fun setupUI() {
        with(binding) {
            viewModel.mediaItem.run {
                imageview.loadImage(artworkUrl100)
                nameTextview.text = existingName
                dateTextview.text = getString(R.string.release_date).plus(formattedDate)
                priceTextview.setPriceText(requireContext(), existingPrice, currency)
                shortDescription?.let {
                    shortDescriptionTextview.visible()
                    shortDescriptionTextview.text = it
                }
                existingLongDescription?.let {
                    longDescriptionTextview.visible()
                    longDescriptionTextview.text = it.toFormatHtml()
                }
            }

            favoriteImageview.setOnClickListener {
                viewModel.toggleFavorite()
                viewModel.checkFavorite()
            }

            backTextview.setOnClickListener {
                findNavController().navigateUp()
            }
        }

    }

    override fun observeViewModel() {
        viewModel.favoriteLiveData.observe(viewLifecycleOwner) {
            binding.favoriteImageview.setFavoriteIcon(it)
        }
    }

}