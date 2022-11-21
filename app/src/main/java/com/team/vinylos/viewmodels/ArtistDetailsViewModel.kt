package com.team.vinylos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.team.vinylos.models.Artist
import com.team.vinylos.repositories.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistDetailsViewModel(application: Application) :  AndroidViewModel(application)  {

    private val artistsRepo = ArtistRepository()

    private val artistsMutableData = MutableLiveData<List<Artist>>()

    val artists: LiveData<List<Artist>>
        get() = artistsMutableData

    init {
        refreshArtists()
    }

    private fun refreshArtists() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    artistsMutableData.postValue(artistsRepo.refreshData())
                }
            }
        }
        catch (e:Exception){
            println("Error")
        }
    }

}