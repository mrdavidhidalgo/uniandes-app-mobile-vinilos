package com.team.vinylos.models

data class CollectorAlbum(

    val id: Int,
    val status:String,
    val album: Album,
    val collector:Collector
)