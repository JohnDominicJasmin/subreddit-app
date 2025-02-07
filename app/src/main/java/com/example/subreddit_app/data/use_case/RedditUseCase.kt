package com.example.subreddit_app.data.use_case

data class RedditUseCase(
    val refreshTokenUseCase: RefreshTokenUseCase,
    val getPopularSubredditUseCase: GetPopularSubredditUseCase,
    val clearTokenUseCase: ClearTokenUseCase,
    val getSavedTokenUseCase: GetSavedTokenUseCase,
    val saveTokenUseCase: SaveTokenUseCase,
    val searchSubredditUseCase: SearchSubredditUseCase,
    val getSubredditDetailsUseCase: GetSubredditDetailsUseCase,
    val getSubredditInfoUseCase: GetSubredditInfoUseCase
)

