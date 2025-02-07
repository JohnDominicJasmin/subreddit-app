package com.example.subreddit_app.data.data_source.network.dto.discussions


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class S(
    @SerializedName("u")
    val u: String,
    @SerializedName("x")
    val x: Int,
    @SerializedName("y")
    val y: Int
)