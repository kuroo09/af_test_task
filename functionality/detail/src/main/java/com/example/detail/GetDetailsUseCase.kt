package com.example.detail

import com.example.functionality.shared.data.abstractions.MetRepository
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val metApi: MetRepository
) {
    operator fun invoke(id: Int) = metApi.getObjectById(id)
}