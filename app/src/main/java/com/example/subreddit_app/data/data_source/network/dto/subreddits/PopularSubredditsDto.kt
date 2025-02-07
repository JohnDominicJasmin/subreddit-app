package com.example.subreddit_app.data.data_source.network.dto.subreddits


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PopularSubredditsDto(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("kind")
    val kind: String
)