package com.example.metmuseum

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.metmuseum.detail.DetailListAdapter
import com.example.metmuseum.gallery.ObjectListAdapter
import com.example.met_api.model.MetObjectId
import com.example.met_api.model.MetPhoto

/**
 * BindingAdapter for ImageView to display image from received URL, a loading animation while URL
 * not ready for use and an error image in case there is no URL.
 */
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

/**
 * BindingAdapter for binding a list of IDs to the RecyclerView.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<com.example.met_api.model.MetObjectId>?) {
    val adapter = recyclerView.adapter as ObjectListAdapter
    adapter.submitList(data)
}

/**
 * BindingAdapter for binding a list of additional images to the RecyclerView.
 */
@BindingAdapter("listData")
fun bindRecyclerViewDetail(recyclerView: RecyclerView, data: List<com.example.met_api.model.MetPhoto>?) {
    val adapter = recyclerView.adapter as DetailListAdapter
    adapter.submitList(data)
}

