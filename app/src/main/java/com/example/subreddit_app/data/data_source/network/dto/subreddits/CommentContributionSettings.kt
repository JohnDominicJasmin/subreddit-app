package com.example.subreddit_app.data.data_source.network.dto.subreddits


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CommentContributionSettings(
    @SerializedName("allowed_media_types")
    val allowedMediaTypes: List<String>
)