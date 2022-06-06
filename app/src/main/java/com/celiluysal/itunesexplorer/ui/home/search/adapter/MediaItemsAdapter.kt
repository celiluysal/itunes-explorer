package com.celiluysal.itunesexplorer.ui.home.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.databinding.ItemMediaItemBinding
import com.celiluysal.itunesexplorer.ui.base.listeners.RecyclerViewListener

class MediaItemsAdapter(
    private val list: List<MediaItem?>,
    private val listener: RecyclerViewListener
) : RecyclerView.Adapter<MediaItemsViewHolder>() {

    override fun onBindViewHolder(holder: MediaItemsViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaItemsViewHolder {
        return MediaItemsViewHolder(
            parent.context,
            ItemMediaItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

}