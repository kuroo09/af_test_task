package com.example.search_ui.state


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entities.SearchModel.Companion.empty
import com.example.search_ui.toSearchModel
import com.example.search.SearchArtUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.example.met_api.Result


/*
* TODOs
* 1-) Convert 2 observation to single one with power of sealed class -> DONE
* 2-) use livedata builder -> not DONE cause implementation for now ok
* */
@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val searchArtUseCase: SearchArtUseCase
) : ViewModel() {

    private val refreshTrigger = MutableSharedFlow<String>(replay = 1)
    val dataFlow = refreshTrigger
        .flatMapLatest { it -> searchObjects(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Result.Success(empty)
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
            emit(Result.Success(searchArtUseCase(userInput).toSearchModel()))
        } catch (e: Exception) {
            emit(Result.Error)
        }
    }

}