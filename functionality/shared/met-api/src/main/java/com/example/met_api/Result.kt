package com.example.met_api

/**
 * Interface for handling displaying LiveData.
 */
sealed interface Result<out T> {
    class Success<T>( val data: T) : Result<T>
    object Loading : Result<Nothing>
    object Error : Result<Nothing>
}
