package com.example.course9feature.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "albums"
)
data class Album(
    val intYearReleased: String,
    @PrimaryKey
    val strAlbum: String,
    var artist: String = "",
    var isLiked: Boolean = false
)