package com.example.metmuseum

/**
 * Interface for handling displaying LiveData.
 */
sealed interface Result<out T> {
    class Success<T>( val data: T) : Result<T>
    object Loading : Result<Nothing>
    object Error : Result<Nothing>
}
