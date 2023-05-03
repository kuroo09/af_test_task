package com.example.metmuseum.network

import com.squareup.moshi.Json

data class MetObject (
    @Json(name = "objectID") val id: Int,
    @Json(name = "primaryImage")val imgUrl: String

    )