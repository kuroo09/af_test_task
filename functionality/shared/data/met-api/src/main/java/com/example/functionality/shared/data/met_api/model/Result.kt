package com.example.functionality.shared.data.met_api.model

/**
 * Interface for handling displaying LiveData.
 */
sealed interface Result<out T> {
    class Success<T>( val data: T) : Result<T>
    object Loading : Result<Nothing>
    object Error : Result<Nothing>
}
