package com.example.course9feature.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.course9feature.model.ArtistResponse
import com.example.course9feature.model.Discography
import com.example.course9feature.repository.Repository
import com.example.course9feature.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _artists: MutableLiveData<Resource<ArtistResponse>> = MutableLiveData()
    val artists: LiveData<Resource<ArtistResponse>> = _artists
    val discography: MutableLiveData<Resource<Discography>> = MutableLiveData()

    fun getResult(keyword: String) = viewModelScope.launch {
        _artists.postValue(Resource.Loading())
        val response = repository.getResult(keyword)
        _artists.postValue(handleArtistsResponse(response))
    }

    fun getDiscography(artist: String) = viewModelScope.launch {
        discography.postValue(Resource.Loading())
        val response = repository.getDiscography(artist)
        discography.postValue(handleDiscographyResponse(response))
    }

    private fun handleDiscographyResponse(response: Response<Discography>): Resource<Discography>? {
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
                if (resultResponse.artists.isNullOrEmpty()) {
                    Log.d("Response", "empty list")
                    return Resource.Error("Empty list", null)
                } else {
                    return Resource.Success(resultResponse)
                }
            }
        }
        return Resource.Error(response.message())
    }
}