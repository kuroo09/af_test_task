package com.example.metmuseum


sealed interface Result<out T> {
    class Success<T>(data: T) : Result<T>
    object Loading : Result<Nothing>
    object Error : Result<Nothing>
}

class Sample(val name: String = "", val surname: String = "")

fun sample() {
    val result = getResult()
    when (result) {
        Result.Error -> TODO()
        Result.Loading -> TODO()
        is Result.Success -> TODO()
    }
}

fun getResult(): Result<Sample> {
    return Result.Success(Sample())
}