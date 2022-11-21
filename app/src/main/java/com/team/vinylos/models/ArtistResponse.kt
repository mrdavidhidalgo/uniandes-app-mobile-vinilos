package com.team.vinylos.models
import com.google.gson.annotations.SerializedName

import java.util.Date
import kotlin.collections.ArrayList

class ArtistResponse {
    @SerializedName("id")
    var id: Number = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("image")
    var image: String? = null

    @SerializedName("birthDate")
    var birthDate: Date? = null

    @SerializedName("creationDate")
    var creationDate: Date? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("albums")
    var albums: ArrayList<AlbumResponse> = ArrayList()
}
