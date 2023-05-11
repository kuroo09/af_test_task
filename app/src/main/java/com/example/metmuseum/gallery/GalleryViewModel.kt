package com.example.metmuseum.gallery

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metmuseum.network.MetApi
import com.example.metmuseum.network.MetObjectId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



/*
* TODOs
* 1-) Convert 2 observation to single one with power of sealed class
* 2-) use livedata builder
* */
class GalleryViewModel : ViewModel() {

    private val _metObjectIdList = MutableLiveData<List<MetObjectId>>()
    val metObjectIdList: LiveData<List<MetObjectId>> = _metObjectIdList

    private val _statusMessage = MutableLiveData<String>()
    val statusMessage: LiveData<String> = _statusMessage

    /**
     * Fetch all objects from the API that fit somehow the user input and create a list of
     * MetObjectId objects to display the result ids.
     */
    fun searchObjects(userInput: String, context: Context) {
        val idList = mutableListOf<MetObjectId>()
        var status = ""
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val idCollectionObject = MetApi.retrofitService.getSearchedObjects(userInput)
                idCollectionObject.objectIds.forEachIndexed { index, id ->
                    idList.add(MetObjectId(id))
                }
            } catch (e: Exception) {
                status = "NO_IDS"
            }
            _metObjectIdList.postValue(idList)
            // change value to show Toast for not finding any fitting id.
            _statusMessage.postValue(status)
        }
    }

}