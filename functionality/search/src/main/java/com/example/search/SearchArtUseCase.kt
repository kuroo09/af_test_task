package com.example.search

import com.example.met_api.MetRepository
import javax.inject.Inject

class SearchArtUseCase @Inject constructor(
    private val metApi: MetRepository
) {
    operator fun invoke(query: String) = metApi.getSearchedObjects(query)
}