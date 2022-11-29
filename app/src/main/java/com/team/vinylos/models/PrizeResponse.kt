package com.team.vinylos.models

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class PrizeResponse {
    @SerializedName("id")
    var id: Number = 0

    @SerializedName("organizatiob")
    var organization: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("performers")
    var performers: ArrayList<PerformerPrizeResponse> = ArrayList()
}