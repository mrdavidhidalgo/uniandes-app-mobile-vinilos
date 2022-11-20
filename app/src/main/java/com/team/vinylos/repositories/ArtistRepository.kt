package com.team.vinylos.repositories

import android.util.Log
import com.team.vinylos.models.Album
import com.team.vinylos.models.Artist
import com.team.vinylos.models.Collector
import com.team.vinylos.network.CacheManager
import com.team.vinylos.network.NetworkAdapter

class ArtistRepository (){
        suspend fun refreshData(onComplete:(resp:List<Artist>)->Unit, onError: (error: Exception)->Unit) {

            var potentialResp = CacheManager.getInstance().get("", Artist::class.java)
            return if(potentialResp==null){
                Log.i("Cache", "from network")
                try {
                    var artists = NetworkAdapter.getArtists()
                    CacheManager.getInstance().put("", artists, Artist::class.java)
                    onComplete(artists)
                }catch (e:Exception){
                    onError(e)
                }
            } else{
                var result = potentialResp as List<Artist>
                Log.i("Cache", "return ${result.size} elements from cache")
                onComplete(result)
            }
    }
}

