package com.example.common

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.functionality.shared.data.met_api.model.BaseItem

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
fun bindRecyclerView(recyclerView: RecyclerView, data: List<BaseItem>?) {
    val adapter = recyclerView.adapter as ListAdapter<BaseItem,*>
    adapter.submitList(data)
}

