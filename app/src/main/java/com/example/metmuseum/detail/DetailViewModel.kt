package com.example.metmuseum.detail

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.metmuseum.network.MetApi
import com.example.metmuseum.network.MetObject
import com.example.metmuseum.network.MetPhoto
import kotlinx.coroutines.launch

class DetailViewModel(metId: Int) : ViewModel() {

    private val _metObject = MutableLiveData<MetObject>()
    val metObject: LiveData<MetObject> = _metObject

    private val _metPhotos = MutableLiveData<List<MetPhoto>>()
    val metPhotos: LiveData<List<MetPhoto>> = _metPhotos

    init {
        getMetObjectById(metId)
    }

    private fun getMetObjectById(metId: Int) {
        viewModelScope.launch {
            try {
                _metObject.value = MetApi.retrofitService.getObjectById(metId)
            } catch (e: Exception) {
                print("long loop error:${e}")
            }
            val urlList = mutableListOf<MetPhoto>()
            _metObject.value?.additionalImages?.forEachIndexed { index, url ->
                urlList.add(MetPhoto(url))
            }
            _metPhotos.postValue(urlList)
            //_metObject.value?.replaceEmptyStrings()
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