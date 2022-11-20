package com.team.vinylos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.team.vinylos.models.Artist
import com.team.vinylos.repositories.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistViewModel(application: Application) :  AndroidViewModel(application)  {

    private val artistsRepo = ArtistRepository()

    private val artistsMutableData = MutableLiveData<List<Artist>>()

    val artists: LiveData<List<Artist>>
        get() = artistsMutableData

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        refreshArtists()
    }
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
    private fun refreshArtists() {

        viewModelScope.launch (Dispatchers.Default){
            withContext(Dispatchers.IO){
                artistsRepo.refreshData({
                    artistsMutableData.postValue(it)
                    _eventNetworkError.postValue(false)
                    _isNetworkErrorShown.postValue (false)
                },{
                    Log.d("Error", it.toString())
                    _eventNetworkError.postValue ( true)
                })
            }
        }
    }

}