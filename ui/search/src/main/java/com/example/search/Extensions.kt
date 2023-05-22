package com.example.search

fun com.example.met_api.model.MetCollectionObject.toSearchModel(): com.example.entities.SearchModel {
    val idList = objectIds.map { com.example.met_api.model.MetObjectId(id = it) }
    return com.example.entities.SearchModel(
        idList
    )
}