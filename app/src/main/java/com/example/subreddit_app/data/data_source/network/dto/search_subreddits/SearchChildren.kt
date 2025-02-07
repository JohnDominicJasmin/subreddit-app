package com.example.subreddit_app.data.data_source.network.dto.search_subreddits


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SearchChildren(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("kind")
    val kind: String
)