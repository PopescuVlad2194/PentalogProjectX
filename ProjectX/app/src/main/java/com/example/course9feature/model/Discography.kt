package com.example.course9feature.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Discography(
    @SerializedName("album")
    val albums: List<Album>
): Serializable