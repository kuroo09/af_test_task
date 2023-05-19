package com.example.entities

import com.example.met_api.model.MetObjectId

/**
 * Class that's getting the mapped data of APIs result.
 */
@JvmInline
value class SearchModel(
    val ids: List<com.example.met_api.model.MetObjectId>
) {
    companion object { val empty = SearchModel(ids = listOf()) }
}