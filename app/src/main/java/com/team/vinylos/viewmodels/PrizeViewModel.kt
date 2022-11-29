package com.team.vinylos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.team.vinylos.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.gson.JsonObject
import com.team.vinylos.models.*
import com.team.vinylos.repositories.PrizeRepository

class PrizeViewModel(application: Application) :  AndroidViewModel(application) {

    private val prizeRepo = PrizeRepository()

    private val prizesMutableData = MutableLiveData<List<Prize>>()

    val prizes: LiveData<List<Prize>>
        get() = prizesMutableData

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    private var _isNetworkErrorShownForCreatePrize = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshPrizes()
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


    private fun refreshPrizes() {
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.IO) {
                prizeRepo.refreshData({
                    prizesMutableData.postValue(it)
                    _eventNetworkError.postValue(false)
                    _isNetworkErrorShown.postValue(false)
                }, {
                    Log.d("Error", it.toString())
                    _eventNetworkError.postValue(true)
                })
            }
        }
    }

    fun createPrize(request : PrizeRequest){
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    prizeRepo.createPrize(prizeToJsonObject(request))
                }
            }
        }
        catch (e:Exception){
            println("Error creando el premio")
        }
    }

    private fun prizeToJsonObject(prize: PrizeRequest): JsonObject {
        val paramObject = JsonObject()
        paramObject.addProperty("organization", prize.organization)
        paramObject.addProperty("name", prize.name)
        paramObject.addProperty("description", prize.description)
        return paramObject
    }


}