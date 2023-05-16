package com.example.metmuseum.gallery


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metmuseum.Result
import com.example.metmuseum.network.MetApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


/*
* TODOs
* 1-) Convert 2 observation to single one with power of sealed class -> DONE
* 2-) use livedata builder -> not DONE cause implementation for now ok
* */
@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val metApi: MetApiService
) : ViewModel() {

    private val refreshTrigger = MutableSharedFlow<String>(replay = 1)
    val dataFlow = refreshTrigger
        .flatMapLatest { it -> searchObjects(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Result.Success(SearchModel.empty)
        )

    fun onSearchSubmit(userInput: String) {
        refreshTrigger.tryEmit(userInput)
    }

    /**
     * Fetch all objects from the API that fit somehow the user input and create a list of
     * MetObjectId objects to display the result ids.
     */
    private suspend fun searchObjects(userInput: String) = flow {
        emit(Result.Loading)
        try {
            emit(Result.Success(metApi.getSearchedObjects(userInput).toSearchModel()))
        } catch (e: Exception) {
            emit(Result.Error)
        }
    }

}