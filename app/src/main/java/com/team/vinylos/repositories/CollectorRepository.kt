package com.team.vinylos.repositories

import android.util.Log
import com.team.vinylos.models.Collector
import com.team.vinylos.network.CacheManager
import com.team.vinylos.network.NetworkAdapter

class CollectorRepository (){
        suspend fun refreshData(onComplete:(resp:List<Collector>)->Unit, onError: (error: Exception)->Unit) {
            var potentialResp = CacheManager.getInstance().get("", Collector::class.java)
            return if(potentialResp==null){
                Log.i("Cache", "from network")
                try {
                    var collectors = NetworkAdapter.getCollectors()
                    CacheManager.getInstance().put("", collectors, Collector::class.java)
                    onComplete(collectors)
                }catch (e:Exception){
                    onError(e)
                }
            } else{
                var result = potentialResp as List<Collector>
                Log.i("Cache", "return ${result.size} elements from cache")
                onComplete(result)
            }
    }
}