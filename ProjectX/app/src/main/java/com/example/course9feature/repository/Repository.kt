package com.example.course9feature.repository

import com.example.course9feature.api.RetrofitInstance
import com.example.course9feature.model.ArtistResponse
import com.example.course9feature.model.Discography
import retrofit2.Response

class Repository {
    suspend fun getResult(keyword: String): Response<ArtistResponse> {
        return RetrofitInstance.api.getArtist(keyword)
    }

    suspend fun getDiscography(artist: String): Response<Discography> {
        return RetrofitInstance.api.getDiscography(artist)
    }
}