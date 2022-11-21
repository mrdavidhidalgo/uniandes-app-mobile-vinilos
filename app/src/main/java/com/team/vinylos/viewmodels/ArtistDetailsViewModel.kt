package com.team.vinylos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.team.vinylos.models.Artist
import com.team.vinylos.repositories.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistDetailsViewModel(application: Application) :  AndroidViewModel(application)  {

    private val artistsRepo = ArtistRepository()
    //val artistId: Int =0

    fun setprueba(artistId: Int)
    {
        refreshArtists(artistId)
    }
    private val artistsMutableData = MutableLiveData<Artist>()

    val artists: LiveData<Artist>
        get() = artistsMutableData


    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    //val id:Int = artistId.toInt()
    //val id: Int =1
    /*init {
        //refreshArtists()
    }*/
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    private fun refreshArtists(id: Int) {

    viewModelScope.launch (Dispatchers.Default){
        withContext(Dispatchers.IO){

            artistsRepo.getArtist(id,{
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


    /*class Factory(val app: Application, val id: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistDetailsViewModel(app, id.toString()) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }*/

}