package com.example.detail

import com.example.functionality.shared.data.met_api.model.Result
import com.example.met_api.MetRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val metApi: MetRepository
) {
    operator fun invoke(id: Int) = metApi.getObjectById(id)
}