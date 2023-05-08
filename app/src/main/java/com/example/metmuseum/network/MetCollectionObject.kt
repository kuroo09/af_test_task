package com.example.metmuseum.network

import com.squareup.moshi.Json
import kotlin.reflect.full.memberProperties

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