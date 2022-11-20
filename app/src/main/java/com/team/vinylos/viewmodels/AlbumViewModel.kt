package com.team.vinylos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.team.vinylos.models.Album
import com.team.vinylos.models.AlbumRequest
import com.team.vinylos.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.gson.JsonObject

class AlbumViewModel(application: Application) :  AndroidViewModel(application)  {

    private val albumsRepo = AlbumRepository()

    private val albumsMutableData = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>>
        get() = albumsMutableData

    init {
        refreshAlbums()
    }

    private fun refreshAlbums() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    albumsMutableData.postValue(albumsRepo.refreshData())
                }
            }
        }
        catch (e:Exception){
            println("Error")
        }
    }

    fun createAlbum(request : AlbumRequest){
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    albumsRepo.createAlbum(albumToJsonObject(request))
                }
            }
        }
        catch (e:Exception){
            println("Error creando album")
        }
    }

    private fun albumToJsonObject(album: AlbumRequest): JsonObject {
        val paramObject = JsonObject()
        paramObject.addProperty("name", album.name)
        paramObject.addProperty("cover", album.cover)
        paramObject.addProperty("releaseDate", ""+album.releaseDate)
        paramObject.addProperty("description", album.description)
        paramObject.addProperty("genre", album.genre)
        paramObject.addProperty("recordLabel", album.recordLabel)
        return paramObject
    }

}