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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {


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
            //getGalleryObjectIds()
            Log.i("Data Fetching", "All Ids fetched")
            val objectList = mutableListOf<MetObject>()
            try {
                Log.i("Data Fetching", "Starting Object fetching")
                for (id in 50..100) {
                    val objectById =
                        MetApi.retrofitService.getObjectById(_idList.value!!.objectIds[id])
                    objectList.add(objectById)
                }
                _metObjects.postValue(objectList)
                Log.i("Data Fetching", "One Object fetched!!!!!")
            } catch (e: Exception) {
            }
        }
    }

    private suspend fun getGalleryObjectIds() {
        try {
            Log.i("DataFetching", "Data loading")
            _idList.value = MetApi.retrofitService.getObjectIds()
            Log.i(
                "Data Fetching",
                "Data received. Last ObjectId = ${_idList.value!!.objectIds.lastIndex}"
            )
        } catch (e: Exception) {
        }
    }

    fun searchObjects(userInput: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(
                "SEARCH",
                "https://collectionapi.metmuseum.org/public/collection/v1/search?q=$userInput"
            )
            try {
                _idList.postValue(MetApi.retrofitService.getSearchedObjects(userInput))
                Log.i("SEARCH", "Found ${_idList.value!!.total} works of art.")
                displayObjects()
            } catch (e: Exception) {
                println(e.toString())
                _metObjects.postValue(listOf())
            }
        }
    }

    private suspend fun displayObjects() {
        val objectList = mutableListOf<MetObject>()
        Log.i("Data Fetching", "Starting Object fetching")
        for (id in _idList.value!!.objectIds) {
            try {
                val objectById = MetApi.retrofitService.getObjectById(id)
                objectList.add(objectById)
            } catch (e: Exception) {
                print("long loop error:${e}")
            }

        }
        _metObjects.postValue(objectList)
    }

}