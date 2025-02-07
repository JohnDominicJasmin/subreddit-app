package com.example.subreddit_app.data.data_source.network.dto.discussions

import com.google.gson.annotations.SerializedName

data class MediaItem(
    @SerializedName("s") val source: ImageSource
)