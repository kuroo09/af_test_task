package com.example.metmuseum.detail

import com.example.metmuseum.network.MetPhoto

/**
 * Sealed Interface to control displaying data.
 */
sealed interface Result {
    data class UIModel(
        val imgUrl: String,
        val title: String,
        val objectDate: String,
        val department: String,
        val repository: String,
        val artist: String,
        val culture: String,
        val imageList: List<MetPhoto>
    ) : Result

    object Loading : Result

    object Error : Result
}