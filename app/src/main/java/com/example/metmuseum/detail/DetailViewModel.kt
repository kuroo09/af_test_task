package com.example.metmuseum.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metmuseum.Result
import com.example.met_api.MetApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


/*
* TODOs
* 1-) Convert 3 observation to single one with power of sealed class -> DONE
* 2-) use livedata builder -> DONE
* 3-) get rid of isLoading as string. Instead, use objects (result of 1. action will cover this one) -> DONE
* 4-) Use Ui object instead of directly accessing the api result -> DONE
* 5-) Use shared Result class
*
* */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val metApi: com.example.met_api.MetApiService
) : ViewModel() {

    /**
     * Observed LiveData that handles displaying the data that's fetched from the API.
     */
    val result: StateFlow<Result<DetailUiModel>> = flow {
        emit(Result.Loading)
        try {
            emit(Result.Success(metApi.getObjectById(getSafeMetId()).toUiModel()))
            println("")
        } catch (e: Exception) {
            emit(Result.Error)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Result.Loading
    )

    private fun getSafeMetId(): Int {
        return savedStateHandle.get<Int>("metId")
            ?: throw IllegalArgumentException("metId can not be null")
    }
}
