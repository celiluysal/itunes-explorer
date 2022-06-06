package com.celiluysal.itunesexplorer.ui.home.search.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.celiluysal.itunesexplorer.R
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.databinding.ItemMediaItemBinding
import com.celiluysal.itunesexplorer.extensions.*
import com.celiluysal.itunesexplorer.ui.base.listeners.RecyclerViewListener
import dagger.hilt.android.qualifiers.ApplicationContext

class MediaItemsViewHolder(
    @ApplicationContext private val context: Context,
    private val binding: ItemMediaItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(mediaItem: MediaItem?, listener: RecyclerViewListener) {
        with(binding) {
            mediaItem?.run {
                mediaItemImageview.loadImage(artworkUrl100)
                mediaItemNameTextview.text = existingName
                mediaItemDateTextview.text = formattedDate
                mediaItemPriceTextview.setPriceText(context, existingPrice, currency)

                root.setOnClickListener {
                    listener.onItemClicked(absoluteAdapterPosition)
                }
            }
        }
    }

}