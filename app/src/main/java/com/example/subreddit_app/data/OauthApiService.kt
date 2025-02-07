package com.example.subreddit_app.data


import com.example.subreddit_app.data.data_source.network.dto.discussion_about.DiscussionAboutDto
import com.example.subreddit_app.data.data_source.network.dto.discussions.DiscussionDto
import com.example.subreddit_app.data.data_source.network.dto.subreddits.PopularSubredditsDto
import com.example.subreddit_app.data.data_source.network.dto.search_subreddits.SubredditSearchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface OauthApiService {

    @GET("subreddits/popular")
    suspend fun getPopularSubreddits(
        @Header("Authorization") authorization: String,
        @Query("limit") limit: Int = 50,
        @Query("include_over_18") nsfw: Boolean = false
    ): PopularSubredditsDto


    @GET("subreddits/search")
    suspend fun searchSubreddits(
        @Header("Authorization") authHeader: String,
        @Header("User-Agent") userAgent: String = "MyRedditApp/1.0",
        @Query("q") query: String,
        @Query("include_over_18") nsfw: Boolean = false,
        @Query("limit") limit: Int = 30
    ): SubredditSearchDto


    @GET("r/{subreddit}/")
    suspend fun getSubredditDetails(
        @Header("Authorization") authorization: String,
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int = 50,
        @Query("include_over_18") nsfw: Boolean = false): DiscussionDto

    @GET("r/{subreddit}/about")
    suspend fun getSubredditInfo(
        @Path("subreddit") subreddit: String,
        @Header("Authorization") authorization: String
    ): DiscussionAboutDto
}