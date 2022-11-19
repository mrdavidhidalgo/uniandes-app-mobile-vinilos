package com.team.vinylos.repositories

import android.util.Log
import com.team.vinylos.models.Album
import com.team.vinylos.models.Artist
import com.team.vinylos.models.Collector
import com.team.vinylos.network.CacheManager
import com.team.vinylos.network.NetworkAdapter

class CollectorRepository (){
        suspend fun refreshData(): List<Collector>{
            var potentialResp = CacheManager.getInstance().get("", Collector::class.java)
            return if(potentialResp==null){
                Log.i("Cache", "from network")
                var collectors = NetworkAdapter.getCollectors()
                CacheManager.getInstance().put("", collectors, Collector::class.java)
                collectors
            } else{
                var result = potentialResp as List<Collector>
                Log.i("Cache", "return ${result.size} elements from cache")
                result
            }
    }
}

