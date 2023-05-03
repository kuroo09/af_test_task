package com.example.metmuseum.gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metmuseum.network.MetApi
import com.example.metmuseum.network.MetCollectionObject
import com.example.metmuseum.network.MetObject
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {

    // internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    // external immutable LiveData for the request status
    val status: LiveData<String>
        get() = _status

    private val _metObjects = MutableLiveData<MetObject>()
    val metObjects: LiveData<MetObject> = _metObjects

    // object from "objects" endpoint that holds every id of MetObjects
    private val _idList = MutableLiveData<MetCollectionObject>()
    val idList: LiveData<MetCollectionObject> = _idList

    // call getGalleryObjects() on init to display immediately
    init {
        getGalleryObjects()
    }

    private fun getGalleryObjects() {
        viewModelScope.launch {
            try {
                Log.i("DataFetching", "Data loading")
                _idList.value = MetApi.retrofitService.getObjectIds()
                _status.value = _idList.value!!.total.toString()
                Log.i("Data Fetching", "Data received. Last Id = ${_idList.value!!.objectIds.lastIndex}")
            } catch (e: Exception) {
                _status.value = "Failure ${e.message}"
            }
        }
    }


}