package com.example.met_api

import com.example.functionality.shared.data.abstractions.MetRepository
import com.example.functionality.shared.data.met_api.model.MetObjectDto
import com.example.functionality.shared.data.met_api.model.SearchCollectionDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MetRepositoryImpl @Inject constructor(
    private val metApi: com.example.functionality.shared.data.met_api.MetApiHelper
) : MetRepository {

    override fun getObjectById(id: Int): Flow<MetObjectDto> {
        return metApi.getObjectById(id).flowOn(Dispatchers.IO)
    }

    override fun getSearchedObjects(query: String): Flow<SearchCollectionDto> {
        return metApi.getSearchedObjects(query).flowOn(Dispatchers.IO)
    }
}