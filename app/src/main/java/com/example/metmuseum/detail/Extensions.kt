package com.example.metmuseum.detail

import com.example.metmuseum.network.MetObject
import com.example.metmuseum.network.MetPhoto

/**
 * Extension function to map the API result object into Result.UIModel object.
 */
fun MetObject.toUiModel(): DetailResult.DetailUiModel {
    val imageList = additionalImages.map { MetPhoto(url = it) }
    return DetailResult.DetailUiModel(
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