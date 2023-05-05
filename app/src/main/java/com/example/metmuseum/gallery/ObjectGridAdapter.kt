package com.example.metmuseum.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.metmuseum.databinding.GridViewItemBinding
import com.example.metmuseum.network.MetObject

class ObjectGridAdapter : ListAdapter<MetObject,
        ObjectGridAdapter.MetObjectViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ObjectGridAdapter.MetObjectViewHolder {
        // return new MetObjectViewHolder created by inflating GridViewItemBinding
        return MetObjectViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ObjectGridAdapter.MetObjectViewHolder, position: Int) {
        // get object associated with current RecyclerView position
        val metObject = getItem(position)
        holder.bind(metObject)
    }

    class MetObjectViewHolder(private var binding: GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(MetObject: MetObject) {
            // bind for displaying grid_view_item
            binding.objectImage = MetObject
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MetObject>() {
        override fun areItemsTheSame(oldItem: MetObject, newItem: MetObject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MetObject, newItem: MetObject): Boolean {
            return oldItem.imgUrl == newItem.imgUrl
        }

    }
}