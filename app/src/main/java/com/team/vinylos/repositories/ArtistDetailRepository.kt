package com.team.vinylos.repositories

import com.team.vinylos.models.Artist
import com.team.vinylos.network.NetworkAdapter

class ArtistDetailRepository (){
        suspend fun refreshData(): List<Artist>{
            return NetworkAdapter.getArtists()
    }
    suspend fun getArtist(id: Int): Artist{
        return NetworkAdapter.getArtist(id)
    }
}

