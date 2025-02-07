package com.example.subreddit_app.data.data_source.network.dto.discussions


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Preview(
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("images")
    val images: List<Image>
)