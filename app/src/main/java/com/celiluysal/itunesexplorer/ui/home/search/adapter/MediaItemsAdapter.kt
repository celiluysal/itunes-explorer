package com.celiluysal.itunesexplorer.ui.home.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.databinding.ItemMediaItemBinding
import com.celiluysal.itunesexplorer.ui.base.listeners.RecyclerViewListener
import com.celiluysal.itunesexplorer.ui.home.search.SearchViewModel

class MediaItemsAdapter(
    private val searchViewModel: SearchViewModel,
    private val listener: RecyclerViewListener
) : PagingDataAdapter<MediaItem, MediaItemsViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: MediaItemsViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaItemsViewHolder {
        return MediaItemsViewHolder(
            ItemMediaItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MediaItem>() {
            override fun areItemsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}