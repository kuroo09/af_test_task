package com.example.search_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.functionality.shared.data.met_api.entities.MetObjectId
import com.example.navigation.MainNavGraphDirections
import com.example.search_ui.databinding.ListViewItemBinding

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
            val action = MainNavGraphDirections.actionGlobalDetailFlow(idView.text.toString().toInt())
            holder.itemView.findNavController().navigate(action)
        }
    }

    class MetObjectViewHolder(private var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(MetObjectId: MetObjectId) {
            // Bind for displaying list_view_item.
            binding.idViewModel = MetObjectId
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MetObjectId>() {
        override fun areItemsTheSame(oldItem: MetObjectId, newItem: MetObjectId): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MetObjectId, newItem: MetObjectId): Boolean {
            return oldItem == newItem
        }

    }
}