package com.team.vinylos.repositories

import android.util.Log
import com.team.vinylos.network.CacheManager
import com.team.vinylos.network.NetworkAdapter
import com.google.gson.JsonObject
import com.team.vinylos.models.*

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

    suspend fun getAlbum(albumId:Integer, onComplete:(resp: Album)->Unit, onError: (error: Exception)->Unit) {
        var potentialResp = CacheManager.getInstance().get(albumId, Album::class.java)

        if(potentialResp==null){
            Log.i("Cache", "from network")
            try {
                var album = NetworkAdapter.getAlbum(albumId)

                CacheManager.getInstance().put(albumId, album, Album::class.java)
                onComplete(album)
            }catch (e:Exception){
                onError(e)
            }

        } else{
            var result = potentialResp as Album
            Log.i("Cache", "return element from cache")
            onComplete(result)
        }
    }

    suspend fun createAlbum(album: JsonObject): AlbumResponse {
        CacheManager.getInstance().invalidate("", Album::class.java)
        return NetworkAdapter.createAlbum(album)
    }

    suspend fun createAlbumComment(albumId: Integer, comment: JsonObject): AlbumCommentResponse {
        Log.i("AlbumComment", "Creando comentario para el album $albumId")
        return NetworkAdapter.createAlbumComment(albumId, comment)
    }
}

