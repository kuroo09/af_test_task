package com.example.metmuseum.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.metmuseum.R
import com.example.metmuseum.databinding.ListViewItemBinding
import com.example.metmuseum.network.MetObject
import com.example.metmuseum.network.MetObjectId

class ObjectListAdapter : ListAdapter<MetObjectId,
        ObjectListAdapter.MetObjectViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ObjectListAdapter.MetObjectViewHolder {
        // return new MetObjectViewHolder created by inflating GridViewItemBinding
        return MetObjectViewHolder(
            ListViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ObjectListAdapter.MetObjectViewHolder, position: Int) {
        // get object associated with current RecyclerView position
        val metObject = getItem(position)
        holder.bind(metObject)

        val idView = holder.itemView.findViewById<TextView>(R.id.met_id)
        idView.setOnClickListener {
            val action = GalleryFragmentDirections.actionGalleryFragmentToDetailFragment(idView.text.toString().toInt())
            holder.itemView.findNavController().navigate(action)
        }

    }

    class MetObjectViewHolder(private var binding: ListViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(MetObjectId: MetObjectId) {
            // bind for displaying grid_view_item
            binding.idViewModel = MetObjectId
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MetObjectId>() {
        override fun areItemsTheSame(oldItem: MetObjectId, newItem: MetObjectId): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MetObjectId, newItem: MetObjectId): Boolean {
            return oldItem.id == newItem.id
        }

    }
}