package com.example.subreddit_app.data.data_source.network.dto.discussions


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AuthorFlairRichtext(
    @SerializedName("a")
    val a: String,
    @SerializedName("e")
    val e: String,
    @SerializedName("u")
    val u: String
)