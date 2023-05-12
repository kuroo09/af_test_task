package com.example.metmuseum.gallery

import com.example.metmuseum.network.MetObjectId

sealed interface SearchResult {
    @JvmInline
    value class SearchModel(
        val ids: List<MetObjectId>
    ) : SearchResult

    object Loading : SearchResult

    object Error : SearchResult

}