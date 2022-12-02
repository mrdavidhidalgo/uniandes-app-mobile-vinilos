package com.team.vinylos.models

import com.google.gson.annotations.SerializedName
import com.team.vinylos.models.Collector

class AlbumCommentResponse{
    @SerializedName("id")
    var id: Number = 0

    @SerializedName("description")
    var description: String? = null

    @SerializedName("rating")
    var rating: Number = 0

    @SerializedName("collector")
    var collector: Collector? = null
}

