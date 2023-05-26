package com.example.met_api.di

import com.example.functionality.shared.data.abstractions.MetRepository
import com.example.met_api.MetRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideRepository(metRepositoryImpl: MetRepositoryImpl): MetRepository
}