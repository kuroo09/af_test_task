package com.example.detail

import com.example.met_api.MetApiService
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val metApi: MetApiService
) {
    suspend operator fun invoke(id: Int) = metApi.getObjectById(id)
}