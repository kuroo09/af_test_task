package com.example.search

import com.example.functionality.shared.data.abstractions.MetRepository
import com.example.functionality.shared.data.met_api.MetApiHelper
import javax.inject.Inject

class SearchArtUseCase @Inject constructor(
    private val metApi: MetRepository
) {
    operator fun invoke(query: String) = metApi.getSearchedObjects(query)
}