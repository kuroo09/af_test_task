package com.example.search_ui.state


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search.SearchArtUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.example.functionality.shared.data.met_api.model.Result
import com.example.functionality.shared.data.met_api.model.SearchCollectionDto
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val searchArtUseCase: SearchArtUseCase
) : ViewModel() {

    private val refreshTrigger = MutableSharedFlow<String>(replay = 1)
    val dataFlow = refreshTrigger
        .flatMapLatest { it ->
            searchObjects(it)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Result.Success(SearchCollectionDto.empty)
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
            emitAll(searchArtUseCase(userInput).map {
                Result.Success(it)
            })
        } catch (e: Exception) {
            emit(Result.Error)
        }
    }

}