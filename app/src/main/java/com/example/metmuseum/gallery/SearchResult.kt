package com.example.metmuseum.gallery

import com.example.metmuseum.network.MetObjectId

/**
 * Class that's getting the mapped data of APIs result.
 */
@JvmInline
value class SearchModel(
    val ids: List<MetObjectId>
)