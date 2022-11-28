package com.team.vinylos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.team.vinylos.models.Album
import com.team.vinylos.models.Collector
import com.team.vinylos.repositories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectorDetailViewModel(application: Application) :  AndroidViewModel(application)  {

    private val collectorsRepo = CollectorRepository()
    private val collectorsMutableData = MutableLiveData<Collector>()
    val collector: LiveData<Collector>
        get() = collectorsMutableData

    private val albumsMutableData = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
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
    public fun refreshCollector(collectorId:Integer) {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    collectorsRepo.refreshData(collectorId,{collector,albums->
                        collectorsMutableData.postValue(collector)
                        albumsMutableData.postValue(albums)
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