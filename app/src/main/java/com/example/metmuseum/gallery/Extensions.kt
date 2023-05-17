package com.example.metmuseum.gallery

import com.example.met_api.model.MetCollectionObject
import com.example.met_api.model.MetObjectId

fun com.example.met_api.model.MetCollectionObject.toSearchModel(): SearchModel {
    val idList = objectIds.map { com.example.met_api.model.MetObjectId(id = it) }
    return SearchModel(
        idList
    )
}