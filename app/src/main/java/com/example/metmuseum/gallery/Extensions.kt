package com.example.metmuseum.gallery

import com.example.metmuseum.network.MetCollectionObject
import com.example.metmuseum.network.MetObjectId

fun MetCollectionObject.toSearchModel(): SearchModel {
    val idList = objectIds.map { MetObjectId(id = it) }
    return SearchModel(
        idList
    )
}