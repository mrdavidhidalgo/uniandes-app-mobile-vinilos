package com.team.vinylos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.team.vinylos.models.Collector
import com.team.vinylos.repositories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectorViewModel(application: Application) :  AndroidViewModel(application)  {

    private val collectorsRepo = CollectorRepository()
    private val collectorsMutableData = MutableLiveData<List<Collector>>()
    val collectors: LiveData<List<Collector>>
        get() = collectorsMutableData

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshCollectors()
    }
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
    private fun refreshCollectors() {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    collectorsRepo.refreshData({
                        collectorsMutableData.postValue(it)
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