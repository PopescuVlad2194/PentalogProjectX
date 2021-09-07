package com.example.course9feature.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "artists"
)
data class Artist(
    @PrimaryKey
    val strArtist: String,
    val strArtistLogo: String,
    val strArtistThumb: String,
    val strArtistWideThumb: String,
    val strBiographyEN: String,
    val strCountry: String,
    val strGender: String,
    val strStyle: String,
    var liked: Boolean = false
)