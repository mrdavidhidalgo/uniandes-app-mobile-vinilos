package com.team.vinylos.repositories

import android.util.Log
import com.team.vinylos.models.Album
import com.team.vinylos.models.Collector
import com.team.vinylos.models.CollectorAlbum
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

    suspend fun getCollector(collectorId:Integer):Collector{
        var potentialResp = CacheManager.getInstance().get(collectorId, Collector::class.java)

        if(potentialResp==null){
            Log.i("Cache", "from network")

            var collector = NetworkAdapter.getCollector(collectorId)

            CacheManager.getInstance().put(collectorId, collector, Collector::class.java)

            return collector
        } else{

            Log.i("Cache", "return element from cache")
            return potentialResp as Collector

        }
    }

    suspend fun getAlbumsByCollector(collectorId:Integer):List<CollectorAlbum>{
        var potentialAlbumResp = CacheManager.getInstance().get(collectorId, CollectorAlbum::class.java)
        if(potentialAlbumResp==null){
            Log.i("Cache", "from network")

            var albums = NetworkAdapter.getAlbumsByCollector(collectorId)

            CacheManager.getInstance().put(collectorId, albums,List::class.java as Class<List<CollectorAlbum>>)

            return albums

        } else{

            Log.i("Cache", "return element from cache")
            return potentialAlbumResp as  List<CollectorAlbum>
        }
    }



    suspend fun refreshData(collectorId:Integer,
                            onComplete:(resp:Collector,respAlbums:List<Album>)->Unit, onError: (error: Exception)->Unit) {

        try {

            var collectorAlbums = getAlbumsByCollector(collectorId);
            var albums = mutableListOf<Album>();
            for (collectorAlbum in collectorAlbums){
                albums.add(collectorAlbum.album)
            }
            var collector = getCollector(collectorId);
            onComplete(collector,albums)
        }catch (e:Exception){
            onError(e)
        }

    }
}