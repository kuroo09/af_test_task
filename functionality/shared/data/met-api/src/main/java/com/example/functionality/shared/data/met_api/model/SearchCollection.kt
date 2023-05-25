package com.example.functionality.shared.data.met_api.model

import com.squareup.moshi.Json

/**
 * Object fetched from the API that holds a list of IDs fitting the search term.
 */
data class SearchCollection(
    val total: Int,
    @Json(name = "objectIDs") val objectIds: List<Int>
)

@JvmInline
value class SearchCollectionDto(
    val ids: List<MetObjectId>
) {
    companion object {
        val empty = SearchCollectionDto(ids = listOf())
    }
}


fun SearchCollection.toDto(): SearchCollectionDto {
    val idList = objectIds.map { MetObjectId(id = it) }
    return SearchCollectionDto(
        idList
    )
}

/**
 * Object for the IDs from the list of MetCollectionObject to display in RecyclerView.
 */
class MetObjectId(
    val id: Int
) : BaseItem()

/**
 * Object for URLs of additional images to display them in RecyclerView.
 */
class MetPhoto(
    val url: String
) : BaseItem()