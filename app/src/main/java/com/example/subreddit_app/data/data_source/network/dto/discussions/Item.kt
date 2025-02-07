package com.example.subreddit_app.data.data_source.network.dto.discussions


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Item(
    @SerializedName("id")
    val id: Int,
    @SerializedName("media_id")
    val mediaId: String
)