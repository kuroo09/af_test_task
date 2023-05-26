package com.example.functionality.shared.data.met_api

import com.example.functionality.shared.data.met_api.model.MetObjectDto
import com.example.functionality.shared.data.met_api.model.SearchCollectionDto
import kotlinx.coroutines.flow.Flow
import com.example.functionality.shared.data.met_api.model.Result

interface MetApiHelper {

    fun getObjectById(id: Int): Flow<Result<MetObjectDto>>

    fun getSearchedObjects(query: String): Flow<Result<SearchCollectionDto>>
}