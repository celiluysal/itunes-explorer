package com.celiluysal.itunesexplorer.ui.home.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.databinding.ItemMediaItemBinding
import com.celiluysal.itunesexplorer.extensions.loadImage
import com.celiluysal.itunesexplorer.ui.base.listeners.RecyclerViewListener

class MediaItemsViewHolder(private val binding: ItemMediaItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(mediaItem: MediaItem?, listener: RecyclerViewListener) {
        binding.mediaItemImageview.loadImage(mediaItem?.artworkUrl100)
        binding.mediaItemNameTextview.text = mediaItem?.collectionName
        binding.mediaItemDateTextview.text = mediaItem?.releaseDate
        binding.mediaItemPriceTextview.text = mediaItem?.collectionPrice.toString()
        binding.root.setOnClickListener {
            listener.onItemClicked(absoluteAdapterPosition)
        }

    }

}