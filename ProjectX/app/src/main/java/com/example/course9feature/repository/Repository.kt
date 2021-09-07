package com.example.course9feature.repository

import com.example.course9feature.api.RetrofitInstance
import com.example.course9feature.db.ArtistDatabase
import com.example.course9feature.model.Artist
import com.example.course9feature.model.ArtistResponse
import com.example.course9feature.model.Discography
import retrofit2.Response

class Repository(
    val db: ArtistDatabase
) {
    suspend fun getResult(keyword: String): Response<ArtistResponse> {
        return RetrofitInstance.api.getArtist(keyword)
    }

    suspend fun getDiscography(artist: String): Response<Discography> {
        return RetrofitInstance.api.getDiscography(artist)
    }

    suspend fun upsert(artist: Artist) = db.getArtistDao().upsert(artist)

    fun getSavedArtists() = db.getArtistDao().getAllArtists()

    suspend fun deleteArtist(artist: Artist) = db.getArtistDao().deleteArtist(artist)

}