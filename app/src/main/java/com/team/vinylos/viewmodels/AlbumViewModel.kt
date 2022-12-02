package com.team.vinylos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.team.vinylos.models.Album
import com.team.vinylos.models.AlbumRequest
import com.team.vinylos.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.gson.JsonObject
import com.team.vinylos.models.AlbumCommentRequest

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumsRepo = AlbumRepository()

    private val albumsMutableData = MutableLiveData<List<Album>>()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    private var _isNetworkErrorShownForCreateAlbum = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshAlbums()
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    val albums: LiveData<List<Album>>
        get() = albumsMutableData

    init {
        refreshAlbums()
    }

    private fun refreshAlbums() {
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.IO) {
                albumsRepo.refreshData({
                    albumsMutableData.postValue(it)
                    _eventNetworkError.postValue(false)
                    _isNetworkErrorShown.postValue(false)
                }, {
                    Log.d("Error", it.toString())
                    _eventNetworkError.postValue(true)
                })
            }
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

    fun createAlbumComment(albumId: Integer, request : AlbumCommentRequest){
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO){
                    albumsRepo.createAlbumComment(albumId, albumCommentToJsonObject(request))
                }
            }
        }
        catch(e: Exception){
            println("Error creando comentario a album")
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

    private fun albumCommentToJsonObject(request : AlbumCommentRequest): JsonObject{
        val paramObject = JsonObject()
        paramObject.addProperty("description", request.description)
        paramObject.addProperty("rating", request.rating)

        val collector = JsonObject()
        collector.addProperty("id", request.collector!!.id)
        collector.addProperty("name", request.collector!!.name)
        collector.addProperty("telephone", request.collector!!.telephone)
        collector.addProperty("email", request.collector!!.email)
        paramObject.add("collector", collector)

        return paramObject
    }


}