package com.example.metmuseum.network

import com.squareup.moshi.Json
import kotlin.reflect.KMutableProperty

data class MetObject (
    @Json(name = "objectID")private var _id: Int,
    @Json(name = "primaryImage")private var _imgUrl: String,
    @Json(name = "title")private var _title: String,
    @Json(name = "objectDate")private var _objectDate: String,
    @Json(name = "department")private var _department: String,
    @Json(name = "repository")private var _repository: String,
    @Json(name = "artistDisplayName")private var _artist: String,
    @Json(name = "culture")private var _culture: String,
    @Json(name = "additionalImages")private var _additionalImages: List<String>
    ) {
    val id: Int
        get() = _id
    val imgUrl: String
        get() = _imgUrl
    val title: String
        get() = _title
    val objectDate: String
        get() = _objectDate
    val department: String
        get() = _department
    val repository: String
        get() = _repository
    val artist: String
        get() = _artist
    val culture: String
        get() = _culture
    val additionalImages: List<String>
        get() = _additionalImages

    fun replaceEmptyStrings() {
        this::class.members.forEach { member ->
            if (member is KMutableProperty<*>) {
                val value = member.call(this)
                if (value is String && value.isBlank()) {
                    member.setter.call(this, "no details")
                }
            }
        }
    }
}