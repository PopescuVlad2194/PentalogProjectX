package com.example.course9feature.data

import com.google.gson.annotations.SerializedName

data class Album (
    val name: String,
    val year: Int,
    @SerializedName("isFavorite")
    var favorite: Boolean
)
