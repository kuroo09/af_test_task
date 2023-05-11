package com.example.metmuseum.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.metmuseum.network.MetApi
import com.example.metmuseum.network.MetObject
import com.example.metmuseum.network.MetPhoto
import kotlinx.coroutines.launch
import java.lang.Thread.sleep


/*
* TODOs
* 1-) Convert 3 observation to single one with power of sealed class
* 2-) use livedata builder
* 3-) get rid of isLoading as string. Instead, use objects (result of 1. action will cover this one)
* 4-) Use Ui object instead of directly accessing the api result
*
* */
class DetailViewModel(metId: Int) : ViewModel() {

    // object fetched from the API
    private val _metObject = MutableLiveData<MetObject>()
    val metObject: LiveData<MetObject> = _metObject

    // list of additional photos of the object
    private val _metPhotos = MutableLiveData<List<MetPhoto>>()
    val metPhotos: LiveData<List<MetPhoto>> = _metPhotos

    // prevents displaying null values while fetching data
    private val _isLoading = MutableLiveData<String>()
    val isLoading: LiveData<String> = _isLoading

    init {
        _isLoading.value = "LOADING"
        getMetObjectById(metId)
    }

    /**
     * Fetch object information data from the API by passing the tapped ID to the query.
     */
    private fun getMetObjectById(metId: Int) {
        viewModelScope.launch {
            try {
                _metObject.value = MetApi.retrofitService.getObjectById(metId)
                _isLoading.value = "NOT_LOADING"
            } catch (e: Exception) {
                _isLoading.value = "ERROR"
            }
            // get list of additional images to display them in recycler view
            val urlList = mutableListOf<MetPhoto>()
            _metObject.value?.additionalImages?.forEachIndexed { _, url ->
                urlList.add(MetPhoto(url))
            }
            _metPhotos.postValue(urlList)
        }
    }
}

class DetailViewModelFactory(private val metId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(metId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}