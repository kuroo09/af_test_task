package com.example.search

import com.example.met_api.MetApiService
import javax.inject.Inject

class SearchArtUseCase @Inject constructor(
    private val metApi: MetApiService
) {
    suspend operator fun invoke(query: String) = metApi.getSearchedObjects(query)
}