package com.example.met_api.di

import com.example.functionality.shared.data.met_api.MetApiHelper
import com.example.functionality.shared.data.met_api.MetApiHelperImpl
import com.example.functionality.shared.data.met_api.MetApiService
import com.example.met_api.MetRepository
import com.example.met_api.MetRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideApiHelper(apiService: MetApiService): MetApiHelper {
        return MetApiHelperImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRepository(metApi: MetApiHelper): MetRepository {
        return MetRepositoryImpl(metApi)
    }
}