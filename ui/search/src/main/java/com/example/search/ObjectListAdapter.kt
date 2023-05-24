package com.example.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.met_api.model.MetObjectId
import com.example.search.databinding.ListViewItemBinding

class ObjectListAdapter :
    ListAdapter<MetObjectId, ObjectListAdapter.MetObjectViewHolder>(
        DiffCallback
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MetObjectViewHolder {
        // Return new MetObjectViewHolder created by inflating GridViewItemBinding.
        return MetObjectViewHolder(
            ListViewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MetObjectViewHolder, position: Int) {
        // Get object associated with current RecyclerView position.
        val metObject = getItem(position)
        holder.bind(metObject)

        val idView = holder.itemView.findViewById<TextView>(R.id.met_id)
        idView.setOnClickListener {
            val action = GalleryFragmentDirections.actionGalleryFragmentToDetailFragment(
                idView.text.toString().toInt()
            )
            holder.itemView.findNavController().navigate(action)
        }

    }

    class MetObjectViewHolder(private var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(MetObjectId: com.example.met_api.model.MetObjectId) {
            // Bind for displaying list_view_item.
            binding.idViewModel = MetObjectId
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<com.example.met_api.model.MetObjectId>() {
        override fun areItemsTheSame(oldItem: com.example.met_api.model.MetObjectId, newItem: com.example.met_api.model.MetObjectId): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: com.example.met_api.model.MetObjectId, newItem: com.example.met_api.model.MetObjectId): Boolean {
            return oldItem.id == newItem.id
        }

    }
}