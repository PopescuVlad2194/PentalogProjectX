package com.example.course9feature.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.course9feature.model.Artist

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(artist: Artist): Long

    @Query("SELECT * FROM artists")
    fun getAllArtists(): LiveData<List<Artist>>

    @Delete
    suspend fun deleteArtist(artist: Artist)
}