package com.team.vinylos.repositories

import com.team.vinylos.models.Album
import com.team.vinylos.network.NetworkAdapter

class AlbumRepository (){
        suspend fun refreshData(): List<Album>{
            return NetworkAdapter.getAlbums()
    }
}

