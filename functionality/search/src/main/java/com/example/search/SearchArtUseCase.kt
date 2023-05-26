package com.example.search

import com.example.functionality.shared.data.abstractions.MetRepository
import javax.inject.Inject

class SearchArtUseCase @Inject constructor(
    private val metApi: com.example.functionality.shared.data.abstractions.MetRepository
) {
    operator fun invoke(query: String) = metApi.getSearchedObjects(query)
}