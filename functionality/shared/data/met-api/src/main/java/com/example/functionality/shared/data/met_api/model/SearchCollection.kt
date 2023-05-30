package com.example.functionality.shared.data.met_api.model

import com.example.functionality.shared.data.met_api.entities.MetObjectId
import com.example.functionality.shared.data.met_api.entities.SearchCollectionDto
import com.squareup.moshi.Json

/**
 * Object fetched from the API that holds a list of IDs fitting the search term.
 */
internal data class SearchCollection(
    val total: Int,
    @Json(name = "objectIDs") val objectIds: List<Int>
)

internal fun SearchCollection.toDto(): SearchCollectionDto {
    val idList = objectIds.map { MetObjectId(id = it) }
    return SearchCollectionDto(
        idList
    )
}

