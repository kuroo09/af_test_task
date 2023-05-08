package com.example.metmuseum.network

import com.squareup.moshi.Json

data class MetCollectionObject (
    val total: Int,
    @Json(name = "objectIDs")val objectIds: List<Int>
        )

class MetObjectId (
    val id: Int
        )

class MetPhoto (
    val url: String
        )