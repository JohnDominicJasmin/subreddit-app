package com.example.subreddit_app.data.data_source.network.dto.token


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class TokenResponseDto(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("token_type")
    val tokenType: String
)