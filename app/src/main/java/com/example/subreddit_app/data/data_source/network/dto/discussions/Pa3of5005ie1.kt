package com.example.subreddit_app.data.data_source.network.dto.discussions


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Pa3of5005ie1(
    @SerializedName("e")
    val e: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("m")
    val m: String,
    @SerializedName("p")
    val p: List<P>,
    @SerializedName("s")
    val s: S,
    @SerializedName("status")
    val status: String
)