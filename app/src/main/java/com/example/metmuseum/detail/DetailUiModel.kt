package com.example.metmuseum.detail

import com.example.metmuseum.network.MetPhoto

/**
 * Detail class that gets the mapped data from the APIs result.
 */
data class DetailUiModel(
    val imgUrl: String,
    val title: String,
    val objectDate: String,
    val department: String,
    val repository: String,
    val artist: String,
    val culture: String,
    val imageList: List<MetPhoto>
)