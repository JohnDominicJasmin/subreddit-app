package com.example.subreddit_app.data.data_source.network.dto.discussions


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Image(
    @SerializedName("id")
    val id: String,
    @SerializedName("resolutions")
    val resolutions: List<Resolution>,
    @SerializedName("source")
    val source: Source,
    @SerializedName("variants")
    val variants: Variants
)