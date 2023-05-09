package com.example.metmuseum

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.metmuseum.detail.DetailListAdapter
import com.example.metmuseum.gallery.ObjectListAdapter
import com.example.metmuseum.network.MetObjectId
import com.example.metmuseum.network.MetPhoto

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        // convert URL string to a Uri object using toUri()
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.baseline_broken_image_24)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MetObjectId>?) {
    val adapter = recyclerView.adapter as ObjectListAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindRecyclerViewDetail(recyclerView: RecyclerView, data: List<MetPhoto>?) {
    val adapter = recyclerView.adapter as DetailListAdapter
    adapter.submitList(data)
}

