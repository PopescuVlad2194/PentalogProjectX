package com.example.course9feature.api

import com.example.course9feature.model.ArtistResponse
import com.example.course9feature.model.Discography
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AudioApi {
    @GET("/api/v1/json/1/search.php")
    suspend fun getArtist(@Query("s") artistName: String): Response<ArtistResponse>

    @GET("/api/v1/json/1/discography.php")
    suspend fun getDiscography(@Query("s") artistName: String): Response<Discography>
}