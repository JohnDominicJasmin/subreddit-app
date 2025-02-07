package com.example.subreddit_app.data.data_source.network.dto.discussions


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GalleryData(
    @SerializedName("items")
    val items: List<Item>
)