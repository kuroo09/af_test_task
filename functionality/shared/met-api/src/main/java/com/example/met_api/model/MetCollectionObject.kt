package com.example.met_api.model

import com.example.met_api.BaseItem

import com.squareup.moshi.Json

//TODO convert suitable classes to value class -> DONE

/**
 * Object fetched from the API that holds a list of IDs fitting the search term.
 */
data class MetCollectionObject(
    val total: Int,
    @Json(name = "objectIDs") val objectIds: List<Int>
)

/**
 * Object for the IDs from the list of MetCollectionObject to display in RecyclerView.
 */
 class MetObjectId(
    val id: Int
): BaseItem()

/**
 * Object for URLs of additional images to display them in RecyclerView.
 */
class MetPhoto(
    val url: String
): BaseItem()