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
import com.example.metmuseum.network.MetObjectId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {

    private val _metObjectIdList = MutableLiveData<List<MetObjectId>>()
    val metObjectIdList: LiveData<List<MetObjectId>> = _metObjectIdList

    fun searchObjects(userInput: String, context: Context) {
        val idList = mutableListOf<MetObjectId>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val idCollectionObject = MetApi.retrofitService.getSearchedObjects(userInput)
                idCollectionObject.objectIds.forEachIndexed { index, id ->
                    idList.add(MetObjectId(id))
                }
            } catch (e: Exception) {
                println(e.toString())
            }
            _metObjectIdList.postValue(idList)
        }
    }

    // call getGalleryObjects() on init to display immediately
    init {
        //getGalleryObjects()
    }



    /*private fun getGalleryObjects() {
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
    }*/


    /*private suspend fun displayObjects() {
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
    }*/

}