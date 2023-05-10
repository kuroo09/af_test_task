package com.example.metmuseum.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"

// Create Moshi object.
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit builder to build and create a Retrofit object.
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Defines how Retrofit talks to web server using HTTP requests.
interface MetApiService {
    /**
     * Fetches the data for requested ID from the API.
     */
    @GET("objects/{id}")
    suspend fun getObjectById(@Path("id") id: Int): MetObject

    /**
     * Fetches object from the API with a list of IDs of all objects fitting the search term.
     */
    @GET("search")
    suspend fun getSearchedObjects(@Query("q") query: String): MetCollectionObject
}

// Singleton object to initialize Retrofit service and accessible form the rest of the app;
// lazy to make sure it's initialized at first usage.
object MetApi {
    val retrofitService : MetApiService by lazy {
        retrofit.create(MetApiService::class.java)
    }
}