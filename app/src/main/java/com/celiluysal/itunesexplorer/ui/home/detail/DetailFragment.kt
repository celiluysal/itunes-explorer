package com.celiluysal.itunesexplorer.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.celiluysal.itunesexplorer.R
import com.celiluysal.itunesexplorer.databinding.FragmentDetailBinding
import com.celiluysal.itunesexplorer.extensions.loadImage
import com.celiluysal.itunesexplorer.extensions.setPriceText
import com.celiluysal.itunesexplorer.extensions.toFormatHtml
import com.celiluysal.itunesexplorer.extensions.visible
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

            backTextview.setOnClickListener {
                findNavController().navigateUp()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}