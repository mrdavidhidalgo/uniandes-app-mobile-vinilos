package com.team.vinylos.repositories

import android.util.Log
import com.team.vinylos.models.Album
import com.team.vinylos.models.Artist
import com.team.vinylos.network.CacheManager
import com.team.vinylos.network.NetworkAdapter

class AlbumRepository (){
        suspend fun refreshData(onComplete:(resp:List<Album>)->Unit, onError: (error: Exception)->Unit) {

            var potentialResp = CacheManager.getInstance().get("", Album::class.java)
            return if(potentialResp==null){
                Log.i("Cache", "from network")
                try {
                    var albums = NetworkAdapter.getAlbums()
                    CacheManager.getInstance().put("", albums, Album::class.java)
                    onComplete(albums)
                }catch (e:Exception){
                    onError(e)
                }
            } else{
                var result = potentialResp as List<Album>
                Log.i("Cache", "return ${result.size} elements from cache")
                onComplete(result)
            }
    }
}

