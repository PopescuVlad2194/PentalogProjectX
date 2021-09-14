package com.example.course9feature.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.course9feature.model.Album

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(album: Album): Long

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): LiveData<List<Album>>

    @Delete
    suspend fun deleteAlbum(album: Album)
}