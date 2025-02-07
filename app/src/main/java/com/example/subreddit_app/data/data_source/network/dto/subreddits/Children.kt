package com.example.subreddit_app.data.data_source.network.dto.subreddits


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Children(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("kind")
    val kind: String
)