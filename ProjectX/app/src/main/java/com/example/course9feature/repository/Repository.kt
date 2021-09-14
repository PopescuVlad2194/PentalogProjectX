package com.example.course9feature.repository

import com.example.course9feature.api.RetrofitInstance
import com.example.course9feature.db.MusicDatabase
import com.example.course9feature.model.Album
import com.example.course9feature.model.Artist
import com.example.course9feature.model.ArtistResponse
import com.example.course9feature.model.Discography
import retrofit2.Response

class Repository(
    private val db: MusicDatabase
) {
    suspend fun getResult(keyword: String): Response<ArtistResponse> {
        return RetrofitInstance.api.getArtist(keyword)
    }

    suspend fun getDiscography(artist: String): Response<Discography> {
        return RetrofitInstance.api.getDiscography(artist)
    }

    suspend fun upsertArtist(artist: Artist) = db.getArtistDao().upsert(artist)

    fun getSavedArtists() = db.getArtistDao().getAllArtists()

    suspend fun deleteArtist(artist: Artist) = db.getArtistDao().deleteArtist(artist)

    suspend fun upsertAlbum(album: Album) = db.getAlbumDao().upsert(album)

    fun getSavedAlbums() = db.getAlbumDao().getAllAlbums()

    suspend fun deleteAlbum(album: Album) = db.getAlbumDao().deleteAlbum(album)

    suspend fun checkForArtist(artist: String) = db.getArtistDao().searchForArtist(artist)
}