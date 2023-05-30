package com.example.functionality.shared.data.met_api.entities

@JvmInline
value class SearchCollectionDto(
    val ids: List<MetObjectId>
) {
    companion object {
        val empty = SearchCollectionDto(ids = listOf())
    }
}