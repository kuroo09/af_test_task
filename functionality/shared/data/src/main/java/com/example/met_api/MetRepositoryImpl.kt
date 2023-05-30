package com.example.met_api

import com.example.functionality.shared.data.abstractions.MetRepository
import com.example.functionality.shared.data.met_api.MetApiHelper
import com.example.functionality.shared.data.met_api.entities.MetObjectDto
import com.example.functionality.shared.data.met_api.entities.SearchCollectionDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import com.example.functionality.shared.data.met_api.entities.Result

class MetRepositoryImpl @Inject constructor(
    private val metApi: MetApiHelper
) : MetRepository {

    override fun getObjectById(id: Int): Flow<Result<MetObjectDto>> {
        return metApi.getObjectById(id).flowOn(Dispatchers.IO)
    }

    override fun getSearchedObjects(query: String): Flow<Result<SearchCollectionDto>> {
        return metApi.getSearchedObjects(query).flowOn(Dispatchers.IO)
    }
}