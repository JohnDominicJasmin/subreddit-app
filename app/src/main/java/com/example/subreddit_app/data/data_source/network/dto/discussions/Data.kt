package com.example.subreddit_app.data.data_source.network.dto.discussions


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Data(
    @SerializedName("after")
    val after: String,
    @SerializedName("before")
    val before: Any,
    @SerializedName("children")
    val children: List<DiscussionChildren>,
    @SerializedName("dist")
    val dist: Int,
    @SerializedName("geo_filter")
    val geoFilter: Any,
    @SerializedName("modhash")
    val modhash: Any
)