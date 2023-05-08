package com.example.metmuseum.network

import com.squareup.moshi.Json
import kotlin.reflect.KMutableProperty

data class MetObject (
    @Json(name = "objectID") val id: Int,
    @Json(name = "primaryImage")val imgUrl: String,
    val title: String,
    val objectDate: String = "-",
    val department: String = "-",
    val repository: String = "-",
    @Json(name = "artistDisplayName")val artist: String = "-",
    //@Json(name = "culture")private var _culture: String = "-",
    val culture: String = "-",
    val additionalImages: List<String>
    ) {
    //val culture: String = _culture

    fun replaceEmptyStrings() {
        this::class.members.forEach { member ->
            if (member is KMutableProperty<*>) {
                val value = member.call(this)
                if (value is String && value.isBlank()) {
                    member.setter.call(this, "https://images.metmuseum.org/CRDImages/ad/original/236336.jpg")
                }
            }
        }
    }
}