package com.example.detail_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.detail_ui.databinding.GridViewItemBinding
import com.example.functionality.shared.data.met_api.model.MetPhoto

/**
 * Adapter class that handles the RecyclerView on Detail screen.
 */
class DetailListAdapter : ListAdapter<MetPhoto, DetailListAdapter.MetPhotoViewHolder>(
    DiffCallback
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MetPhotoViewHolder {
        return MetPhotoViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: MetPhotoViewHolder, position: Int) {
        val metPhoto = getItem(position)
        holder.bind(metPhoto)
    }

    class MetPhotoViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(metPhoto: MetPhoto) {
            binding.objectImage = metPhoto
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MetPhoto>() {
        override fun areItemsTheSame(oldItem: MetPhoto, newItem: MetPhoto): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: MetPhoto, newItem: MetPhoto): Boolean {
            return oldItem.url == newItem.url
        }

    }
}