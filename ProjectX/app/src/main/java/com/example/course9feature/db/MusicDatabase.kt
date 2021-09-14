package com.example.course9feature.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.course9feature.R
import com.example.course9feature.model.Album
import com.example.course9feature.model.Artist

@Database(
    entities = [Artist::class, Album::class],
    version = 1
)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun getArtistDao(): ArtistDao
    abstract fun getAlbumDao(): AlbumDao

    companion object {
        @Volatile
        private var instance: MusicDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MusicDatabase::class.java,
                context.getString(R.string.db_name)
            ).build()
    }
}