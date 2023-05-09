package com.example.metmuseum.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.metmuseum.network.MetApi
import com.example.metmuseum.network.MetObject
import com.example.metmuseum.network.MetPhoto
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class DetailViewModel(metId: Int) : ViewModel() {

    // object fetched from the API
    private val _metObject = MutableLiveData<MetObject>()
    val metObject: LiveData<MetObject> = _metObject

    // list of additional photos of the object
    private val _metPhotos = MutableLiveData<List<MetPhoto>>()
    val metPhotos: LiveData<List<MetPhoto>> = _metPhotos

    // prevents displaying null values while fetching data
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    init {
        _isLoading.value = true
        getMetObjectById(metId)
    }

    /**
     * Fetch object information data from the API by passing the tapped ID to the query.
     */
    private fun getMetObjectById(metId: Int) {
        viewModelScope.launch {
            val metObject: MetObject
            try {
                _metObject.value = MetApi.retrofitService.getObjectById(metId)
                _isLoading.value = false
            } catch (e: Exception) {
                print("long loop error:${e}")
            }
            // get list of additional images to display them in recycler view
            val urlList = mutableListOf<MetPhoto>()
            _metObject.value?.additionalImages?.forEachIndexed { index, url ->
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