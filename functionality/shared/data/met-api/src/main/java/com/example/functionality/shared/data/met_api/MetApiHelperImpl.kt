package com.example.functionality.shared.data.met_api

import com.example.functionality.shared.data.met_api.model.MetObjectDto
import com.example.functionality.shared.data.met_api.model.SearchCollectionDto
import com.example.functionality.shared.data.met_api.model.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.functionality.shared.data.met_api.model.Result

internal class MetApiHelperImpl @Inject internal constructor(private val apiService: MetApiService) : MetApiHelper {

    override fun getObjectById(id: Int): Flow<Result<MetObjectDto>> = flow {
        emit(Result.Loading)
        try {
            emit(Result.Success(apiService.getObjectById(id).toDto()))
        } catch (e: Exception) {
            emit(Result.Error)
        }
    }

    override fun getSearchedObjects(query: String): Flow<Result<SearchCollectionDto>> = flow {
        emit (Result.Loading)
        try {
            emit(Result.Success(apiService.getSearchedObjects(query).toDto()))
        } catch (e: Exception) {
            emit(Result.Error)
        }
    }
}
