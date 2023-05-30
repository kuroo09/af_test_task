package com.example.functionality.shared.data.met_api.entities

import com.example.functionality.shared.data.met_api.entities.BaseItem

/**
 * Object for URLs of additional images to display them in RecyclerView.
 */
data class MetPhotoUrl(
    val url: String
) : BaseItem()