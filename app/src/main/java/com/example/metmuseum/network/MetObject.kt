package com.example.metmuseum.network

import com.squareup.moshi.Json

data class MetObject (
    @Json(name = "objectID") val id: Int,
    @Json(name = "primaryImage")val imgUrl: String,
    val title: String,
    val objectDate: String = "-",
    val department: String = "-",
    val repository: String = "-",
    @Json(name = "artistDisplayName")val artist: String = "-",
    val culture: String = "-",
    val additionalImages: List<String>
    )