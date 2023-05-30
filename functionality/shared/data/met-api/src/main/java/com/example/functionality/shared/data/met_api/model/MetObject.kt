package com.example.functionality.shared.data.met_api.model

import com.example.functionality.shared.data.met_api.entities.MetObjectDto
import com.example.functionality.shared.data.met_api.entities.MetPhotoUrl
import com.squareup.moshi.Json

/**
 * Object holding all data for the requested ID fetched from the API.
 */
data class MetObject(
    @Json(name = "objectID") val id: Int,
    @Json(name = "primaryImage") val imgUrl: String,
    val title: String,
    val objectDate: String,
    val department: String,
    val repository: String,
    @Json(name = "artistDisplayName") val artist: String,
    val culture: String,
    val additionalImages: List<String>
)

internal fun MetObject.toDto(): MetObjectDto {
    val imageList = additionalImages.map { MetPhotoUrl(url = it) }
    return MetObjectDto(
        imgUrl,
        title,
        objectDate,
        department,
        repository,
        artist,
        culture,
        imageList
    )
}