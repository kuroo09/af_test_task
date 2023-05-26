package com.example.functionality.shared.data.met_api

import com.example.functionality.shared.data.met_api.model.MetObjectDto
import com.example.functionality.shared.data.met_api.model.SearchCollectionDto
import com.example.functionality.shared.data.met_api.model.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MetApiHelperImpl @Inject internal constructor(private val apiService: MetApiService) : MetApiHelper {

    override fun getObjectById(id: Int): Flow<MetObjectDto> = flow {
        emit(apiService.getObjectById(id).toDto())
    }

    override fun getSearchedObjects(query: String): Flow<SearchCollectionDto> = flow {
        emit(apiService.getSearchedObjects(query).toDto())
    }
}
