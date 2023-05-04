package com.example.metmuseum.gallery

import android.content.Context
import android.util.Log
import android.widget.Toast
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

    private val _metObjects = MutableLiveData<List<MetObject>>()
    val metObjects: LiveData<List<MetObject>> = _metObjects

    // object from "objects" endpoint that holds every id of MetObjects
    private val _idList = MutableLiveData<MetCollectionObject>()
    val idList: LiveData<MetCollectionObject> = _idList

    // call getGalleryObjects() on init to display immediately
    init {
        //getGalleryObjects()
    }

    private fun getGalleryObjects() {
        viewModelScope.launch {
            getGalleryObjectIds()
            Log.i("Data Fetching", "All Ids fetched")
            val objectList = mutableListOf<MetObject>()
            try {
                Log.i("Data Fetching", "Starting Object fetching")
                for (id in 50..100) {
                    val objectById = MetApi.retrofitService.getObjectById(_idList.value!!.objectIds[id])
                    objectList.add(objectById)
                }
                _metObjects.value = objectList
                _status.value = "Success. All Data FETCHED"
                Log.i("Data Fetching", "One Object fetched!!!!!")
            } catch (e: Exception) {
                _status.value = "Failure ${e.message}"
            }
        }
    }

    private suspend fun getGalleryObjectIds() {
        try {
            Log.i("DataFetching", "Data loading")
            _idList.value = MetApi.retrofitService.getObjectIds()
            _status.value = _idList.value!!.total.toString()
            Log.i("Data Fetching", "Data received. Last ObjectId = ${_idList.value!!.objectIds.lastIndex}")
        } catch (e: Exception) {
            _status.value = "Failure while ID fetching ${e.message}"
        }
    }

    fun searchObjects(id: Int, context: Context) {
        viewModelScope.launch {
            try {
                _metObjects.value = listOf(MetApi.retrofitService.getObjectById(id))
                Log.i("SEARCH", "${_metObjects.value!![0].imgUrl}")
            }catch (e: Exception) {
                _metObjects.value = listOf()
                Toast.makeText(context, "Searched ID does not exist.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}