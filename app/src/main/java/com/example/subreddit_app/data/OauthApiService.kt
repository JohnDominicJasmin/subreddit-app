package com.example.subreddit_app.data


import com.example.subreddit_app.data.data_source.network.dto.subreddits.SubRedditsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface OauthApiService {

    @GET("subreddits/popular")
    suspend fun getPopularSubreddits(
        @Header("Authorization") authorization: String
    ): Response<SubRedditsDto>


}