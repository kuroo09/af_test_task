package com.example.metmuseum.detail

import com.example.metmuseum.Result
import com.example.metmuseum.Sample
import com.example.metmuseum.network.MetPhoto

/**
 * Sealed Interface to control displaying data.
 */
sealed interface DetailResult {
    data class DetailUiModel(
        val imgUrl: String,
        val title: String,
        val objectDate: String,
        val department: String,
        val repository: String,
        val artist: String,
        val culture: String,
        val imageList: List<MetPhoto>
    ) : DetailResult

    object Loading : DetailResult

    object Error : DetailResult
}