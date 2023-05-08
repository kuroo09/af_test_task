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
import kotlinx.coroutines.launch

class DetailViewModel(metId: Int) : ViewModel() {

    private val _metObject = MutableLiveData<MetObject>()
    val metObject: LiveData<MetObject> = _metObject

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