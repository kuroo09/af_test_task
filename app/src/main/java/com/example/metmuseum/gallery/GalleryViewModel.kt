package com.example.metmuseum.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.metmuseum.Result
import com.example.metmuseum.network.MetApiService
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _metObjectIdList = MutableLiveData<Result<SearchModel>>()
    val metObjectIdList: LiveData<Result<SearchModel>> = _metObjectIdList

    /**
     * Fetch all objects from the API that fit somehow the user input and create a list of
     * MetObjectId objects to display the result ids.
     */
    fun searchObjects(userInput: String) {
        _metObjectIdList.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _metObjectIdList.postValue(Result.Success(metApi.getSearchedObjects(userInput).toSearchModel()))
            } catch (e: Exception) {
                _metObjectIdList.postValue(Result.Error)
            }
        }
    }

}