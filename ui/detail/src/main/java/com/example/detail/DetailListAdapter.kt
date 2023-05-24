package com.example.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.detail.databinding.GridViewItemBinding
import com.example.met_api.model.MetPhoto

/**
 * Adapter class that handles the RecyclerView on Detail screen.
 */
class DetailListAdapter : ListAdapter<com.example.met_api.model.MetPhoto, DetailListAdapter.MetPhotoViewHolder>(
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
        fun bind(metPhoto: com.example.met_api.model.MetPhoto) {
            binding.objectImage = metPhoto
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<com.example.met_api.model.MetPhoto>() {
        override fun areItemsTheSame(oldItem: com.example.met_api.model.MetPhoto, newItem: com.example.met_api.model.MetPhoto): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: com.example.met_api.model.MetPhoto, newItem: com.example.met_api.model.MetPhoto): Boolean {
            return oldItem.url == newItem.url
        }

    }
}