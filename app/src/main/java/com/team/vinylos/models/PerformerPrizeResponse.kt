package com.team.vinylos.models
import com.google.gson.annotations.SerializedName

import java.util.Date
import kotlin.collections.ArrayList

class PerformerPrizeResponse{
    @SerializedName("id")
    var id: Number = 0;

    @SerializedName("premiationDate")
    val premiationDate: String? = null;
}
