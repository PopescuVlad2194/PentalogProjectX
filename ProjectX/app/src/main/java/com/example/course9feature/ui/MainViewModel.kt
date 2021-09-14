package com.example.course9feature.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.course9feature.model.Album
import com.example.course9feature.model.Artist
import com.example.course9feature.model.ArtistResponse
import com.example.course9feature.model.Discography
import com.example.course9feature.repository.Repository
import com.example.course9feature.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _artists: MutableLiveData<Resource<ArtistResponse>> = MutableLiveData()
    val artists: LiveData<Resource<ArtistResponse>> = _artists
    private val _discography: MutableLiveData<Resource<Discography>> = MutableLiveData()
    val discography: LiveData<Resource<Discography>> = _discography
    private val _isFavorite: MutableLiveData<Artist> = MutableLiveData()
    val isFavorite: LiveData<Artist> = _isFavorite


    fun getResult(keyword: String) = viewModelScope.launch {
        _artists.postValue(Resource.Loading())
        val response = repository.getResult(keyword)
        _artists.postValue(handleArtistsResponse(response))
    }

    fun getDiscography(artist: String) = viewModelScope.launch {
        _discography.postValue(Resource.Loading())
        val response = repository.getDiscography(artist)
        _discography.postValue(handleDiscographyResponse(response))
    }

    private fun handleDiscographyResponse(response: Response<Discography>): Resource<Discography> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                Log.d("Response", resultResponse.toString())
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleArtistsResponse(response: Response<ArtistResponse>): Resource<ArtistResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return if (resultResponse.artists.isNullOrEmpty()) {
                    Resource.Error("Empty list", null)
                } else {
                    Resource.Success(resultResponse)
                }
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArtist(artist: Artist) = viewModelScope.launch {
        repository.upsertArtist(artist)
    }

    fun getSavedArtists() = repository.getSavedArtists()

    fun deleteArtist(artist: Artist) = viewModelScope.launch {
        repository.deleteArtist(artist)
    }

    fun saveAlbum(album: Album) = viewModelScope.launch {
        repository.upsertAlbum(album)
    }

    fun getSavedAlbums() = repository.getSavedAlbums()

    fun deleteAlbum(album: Album) = viewModelScope.launch {
        repository.deleteAlbum(album)
    }

    fun checkForArtist(artist: String) = viewModelScope.launch {
        val response = repository.checkForArtist(artist)
        _isFavorite.postValue(response)
    }

}