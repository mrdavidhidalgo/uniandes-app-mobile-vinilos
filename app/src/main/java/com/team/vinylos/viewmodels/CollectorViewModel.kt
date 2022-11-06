package com.team.vinylos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.team.vinylos.models.Collector
import com.team.vinylos.repositories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectorViewModel(application: Application) :  AndroidViewModel(application)  {

    init {
        refreshCollectors()
    }

    private val collectorsRepo = CollectorRepository()

    private val collectorsMutableData = MutableLiveData<List<Collector>>()

    val collectors: LiveData<List<Collector>>
        get() = collectorsMutableData

    private fun refreshCollectors() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    collectorsMutableData.postValue(collectorsRepo.refreshData())
                }
            }
        }
        catch (e:Exception){
            println("Error")
        }
    }

}