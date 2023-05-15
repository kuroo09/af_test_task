package com.example.metmuseum.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.metmuseum.Result
import com.example.metmuseum.network.MetApi


/*
* TODOs
* 1-) Convert 3 observation to single one with power of sealed class -> DONE
* 2-) use livedata builder -> DONE
* 3-) get rid of isLoading as string. Instead, use objects (result of 1. action will cover this one) -> DONE
* 4-) Use Ui object instead of directly accessing the api result -> DONE
* 5-) Use shared Result class
*
* */
class DetailViewModel(metId: Int) : ViewModel() {

    /**
     * Observed LiveData that handles displaying the data that's fetched from the API.
     */
    val result: LiveData<Result<DetailUiModel>> = liveData {
        emit(Result.Loading)
        try {
            emit(Result.Success(MetApi.retrofitService.getObjectById(metId).toUiModel()))
            println("")
        } catch (e: Exception) {
            emit(Result.Error)
        }
    }

}

class DetailViewModelFactory(private val metId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(metId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}