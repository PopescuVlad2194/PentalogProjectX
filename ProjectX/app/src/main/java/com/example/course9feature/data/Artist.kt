package com.example.course9feature.data

import com.google.gson.annotations.SerializedName

data class Artist(
    val name: String,
    val thumbnail: String,
    val genre: String,
    @SerializedName("isLiked")
    var liked: Boolean
)