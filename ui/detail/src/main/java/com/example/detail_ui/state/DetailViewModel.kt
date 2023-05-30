package com.example.detail_ui.state

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detail.GetDetailsUseCase
import com.example.functionality.shared.data.met_api.entities.Result
import com.example.functionality.shared.data.met_api.entities.MetObjectDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getDetailUseCase: GetDetailsUseCase
) : ViewModel() {

    /**
     * Observed LiveData that handles displaying the data that's fetched from the API.
     */
    val result: StateFlow<Result<MetObjectDto>> = getDetailUseCase(savedStateHandle.get<Int>("metId")!!)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Result.Loading
        )

/*    private fun getSafeMetId(): Int {
        return savedStateHandle.get<Int>("metId")
            ?: throw IllegalArgumentException("metId can not be null")
    }*/
}
