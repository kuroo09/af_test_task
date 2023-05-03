package com.example.metmuseum.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"

// create Moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit builder to build and create a Retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// defines how Retrofit talks to web server using HTTP requests
interface MetApiService {
    @GET("objects")
    suspend fun getObjectIds(): MetCollectionObject

    @GET("objects/{id}")
    suspend fun getObjectById(@Path("id") id: Int): MetObject
}

// singleton object to initialize Retrofit service and accessible form the rest of the app
// lazy to make sure it's initialized at first usage
object MetApi {
    val retrofitService : MetApiService by lazy {
        retrofit.create(MetApiService::class.java)
    }
}