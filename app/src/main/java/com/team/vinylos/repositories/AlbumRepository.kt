package com.team.vinylos.repositories

import com.team.vinylos.models.Album
import com.team.vinylos.models.AlbumResponse
import com.team.vinylos.network.NetworkAdapter
import com.google.gson.JsonObject

class AlbumRepository (){
    suspend fun refreshData(): List<Album>{
        return NetworkAdapter.getAlbums()
    }

    suspend fun createAlbum(album: JsonObject): AlbumResponse {
        return NetworkAdapter.createAlbum(album)
    }
}

