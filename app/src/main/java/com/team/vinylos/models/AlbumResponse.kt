package com.team.vinylos.models

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class AlbumResponse {
    @SerializedName("id")
    var id: Number = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("cover")
    var cover: String? = null

    @SerializedName("releaseDate")
    var releaseDate: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("genre")
    var genre: String? = null

    @SerializedName("recordLabel")
    var recordLabel: String? = null

    @SerializedName("performers")
    var performers: ArrayList<ArtistResponse> = ArrayList()
}

