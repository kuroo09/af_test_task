package com.example.functionality.shared.data.met_api

import com.example.functionality.shared.data.met_api.model.MetObjectDto
import com.example.functionality.shared.data.met_api.model.SearchCollectionDto
import kotlinx.coroutines.flow.Flow

interface MetApiHelper {

    fun getObjectById(id: Int): Flow<MetObjectDto>

    fun getSearchedObjects(query: String): Flow<SearchCollectionDto>
}