package com.example.subreddit_app.data

import com.example.subreddit_app.data.data_source.network.dto.token.TokenResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("access_token")
    @FormUrlEncoded
    suspend fun refreshAccessToken(
        @Header("Authorization") authorization: String, // This will be your base64 encoded username and password
        @Field("grant_type") grantType: String = "password", // Using 'password' grant type for username and password
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<TokenResponseDto>
}
