package com.team.vinylos.repositories

import android.util.Log
import com.team.vinylos.network.CacheManager
import com.team.vinylos.network.NetworkAdapter

import com.google.gson.JsonObject
import com.team.vinylos.models.*

class PrizeRepository (){
    suspend fun refreshData(onComplete:(resp:List<Prize>)->Unit, onError: (error: Exception)->Unit) {

        var potentialResp = CacheManager.getInstance().get("", Prize::class.java)
        return if(potentialResp==null){
            Log.i("Cache", "from network")
            try {
                var prizes = NetworkAdapter.getPrizes()
                CacheManager.getInstance().put("", prizes, Prize::class.java)
                onComplete(prizes)
            }catch (e:Exception){
                onError(e)
            }
        } else{
            var result = potentialResp as List<Prize>
            Log.i("Cache", "return ${result.size} elements from cache")
            onComplete(result)
        }
    }

    suspend fun createPrize(prize: JsonObject): PrizeResponse {
        CacheManager.getInstance().invalidate("", Prize::class.java)
        return NetworkAdapter.createPrize(prize)
    }
}