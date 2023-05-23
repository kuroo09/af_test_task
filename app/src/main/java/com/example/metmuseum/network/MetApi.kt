package com.example.metmuseum.network

import com.example.metmuseum.detail.DetailUiModel
import com.example.metmuseum.gallery.SearchModel
import kotlinx.coroutines.flow.Flow


interface MetApi {

    fun getObjectById(id: Int): Flow<DetailUiModel>

    fun getSearchedObjects(query: String): Flow<SearchModel>
}