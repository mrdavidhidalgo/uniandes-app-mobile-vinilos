package com.team.vinylos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.team.vinylos.models.Artist
import com.team.vinylos.repositories.ArtistDetailRepository
import com.team.vinylos.repositories.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistDetailsViewModel(application: Application) :  AndroidViewModel(application)  {

    private val artistsRepo = ArtistDetailRepository()
    //val artistId: Int =0

    fun setprueba(artistId: Int)
    {
        refreshArtists(artistId)
    }
    private val artistsMutableData = MutableLiveData<Artist>()

    val artists: LiveData<Artist>
        get() = artistsMutableData

    //val id:Int = artistId.toInt()
    //val id: Int =1
    /*init {
        //refreshArtists()
    }*/

    private fun refreshArtists(id: Int) {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    artistsMutableData.postValue(artistsRepo.getArtist(id))
                }
            }
        }
        catch (e:Exception){
            println("Error")
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