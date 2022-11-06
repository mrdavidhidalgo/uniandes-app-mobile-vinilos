package com.team.vinylos.repositories

import com.team.vinylos.models.Album
import com.team.vinylos.models.Collector
import com.team.vinylos.network.NetworkAdapter

class CollectorRepository (){
        suspend fun refreshData(): List<Collector>{
            return NetworkAdapter.getCollectors()
    }
}

