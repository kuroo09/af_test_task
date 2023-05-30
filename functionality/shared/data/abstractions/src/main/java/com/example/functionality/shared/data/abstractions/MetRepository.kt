package com.example.functionality.shared.data.abstractions

import com.example.functionality.shared.data.met_api.entities.MetObjectDto
import com.example.functionality.shared.data.met_api.entities.SearchCollectionDto
import kotlinx.coroutines.flow.Flow
import com.example.functionality.shared.data.met_api.entities.Result

interface MetRepository {

    fun getObjectById(id: Int): Flow<Result<MetObjectDto>>

    fun getSearchedObjects(query: String): Flow<Result<SearchCollectionDto>>
}