package com.team.vinylos.repositories

import com.team.vinylos.models.Artist
import com.team.vinylos.network.NetworkAdapter

class ArtistRepository (){
        suspend fun refreshData(): List<Artist>{
            return NetworkAdapter.getArtists()
    }
}

