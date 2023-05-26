package com.example.functionality.shared.data.met_api

import com.example.functionality.shared.data.met_api.model.MetObject
import com.example.functionality.shared.data.met_api.model.SearchCollection
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"

// Defines how Retrofit talks to web server using HTTP requests.
internal interface MetApiService {
    /**
     * Fetches the data for requested ID from the API.
     */
    @GET("objects/{id}")
    suspend fun getObjectById(@Path("id") id: Int): MetObject

    /**
     * Fetches object from the API with a list of IDs of all objects fitting the search term.
     */
    @GET("search")
    suspend fun getSearchedObjects(@Query("q") query: String): SearchCollection
}