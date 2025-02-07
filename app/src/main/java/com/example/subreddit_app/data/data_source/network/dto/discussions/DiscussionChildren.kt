package com.example.subreddit_app.data.data_source.network.dto.discussions


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DiscussionChildren(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("kind")
    val kind: String
)