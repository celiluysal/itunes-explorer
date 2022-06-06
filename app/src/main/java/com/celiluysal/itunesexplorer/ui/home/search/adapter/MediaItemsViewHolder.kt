package com.celiluysal.itunesexplorer.ui.home.search.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.databinding.ItemMediaItemBinding
import com.celiluysal.itunesexplorer.extensions.*
import com.celiluysal.itunesexplorer.ui.base.listeners.RecyclerViewListener

class MediaItemsViewHolder(
    private val context: Context,
    private val binding: ItemMediaItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(mediaItem: MediaItem?, listener: RecyclerViewListener) {
        with(binding) {
            mediaItem?.run {
                imageview.loadImage(artworkUrl100)
                nameTextview.text = existingName
                dateTextview.text = formattedDate
                priceTextview.setPriceText(context, existingPrice, currency)

                root.setOnClickListener {
                    listener.onItemClicked(
                        adapterPosition)
                }
            }
        }
    }

}