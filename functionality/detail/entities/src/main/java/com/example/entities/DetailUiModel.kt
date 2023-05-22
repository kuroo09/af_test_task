package com.example.entities

import com.example.met_api.model.MetPhoto

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
    val imageList: List<com.example.met_api.model.MetPhoto>
)