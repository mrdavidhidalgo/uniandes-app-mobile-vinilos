package com.team.vinylos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.team.vinylos.models.Album
import com.team.vinylos.models.Collector
import com.team.vinylos.repositories.AlbumRepository
import com.team.vinylos.repositories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumDetailViewModel(application: Application) :  AndroidViewModel(application)  {

    private val albumsRepo = AlbumRepository()
    private val albumsMutableData = MutableLiveData<Album>()
    val album: LiveData<Album>
        get() = albumsMutableData

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }



    public fun refreshAlbum(albumId: Integer) {

        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.IO) {

                albumsRepo.getAlbum(albumId, {
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