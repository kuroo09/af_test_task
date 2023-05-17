package com.example.metmuseum.detail

import com.example.met_api.model.MetObject
import com.example.met_api.model.MetPhoto

/**
 * Extension function to map the API result object into Result.UIModel object.
 */
fun com.example.met_api.model.MetObject.toUiModel(): DetailUiModel {
    val imageList = additionalImages.map { com.example.met_api.model.MetPhoto(url = it) }
    return DetailUiModel(
        imgUrl,
        title,
        objectDate,
        department,
        repository,
        artist,
        culture,
        imageList
    )
}