package com.team.vinylos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.team.vinylos.models.Album
import com.team.vinylos.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumsRepo = AlbumRepository()

    private val albumsMutableData = MutableLiveData<List<Album>>()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

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
}