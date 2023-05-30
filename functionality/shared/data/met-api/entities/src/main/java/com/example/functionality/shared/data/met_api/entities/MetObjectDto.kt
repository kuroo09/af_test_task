package com.example.functionality.shared.data.met_api.entities

data class MetObjectDto (
    val imgUrl: String,
    val title: String,
    val objectDate: String,
    val department: String,
    val repository: String,
    val artist: String,
    val culture: String,
    val imageList: List<MetPhotoUrl>
)