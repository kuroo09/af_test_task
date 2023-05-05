package com.example.metmuseum

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.metmuseum.gallery.ObjectListAdapter
import com.example.metmuseum.network.MetObject
import com.example.metmuseum.network.MetObjectId

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        // convert URL string to a Uri object using toUri()
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_image)
            error(R.drawable.baseline_broken_image_24)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MetObjectId>?) {
    val adapter = recyclerView.adapter as ObjectListAdapter
    adapter.submitList(data)
}