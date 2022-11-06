package com.team.vinylos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.team.vinylos.models.Album
import com.team.vinylos.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

}