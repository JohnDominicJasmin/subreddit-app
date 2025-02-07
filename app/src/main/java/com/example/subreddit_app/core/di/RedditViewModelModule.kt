package com.example.subreddit_app.core.di

import com.example.subreddit_app.data.data_source.local.TokenManager
import com.example.subreddit_app.data.use_case.ClearTokenUseCase
import com.example.subreddit_app.data.use_case.GetPopularSubredditUseCase
import com.example.subreddit_app.data.use_case.GetSavedTokenUseCase
import com.example.subreddit_app.data.use_case.GetSubredditDetailsUseCase
import com.example.subreddit_app.data.use_case.GetSubredditInfoUseCase
import com.example.subreddit_app.data.use_case.RedditUseCase
import com.example.subreddit_app.data.use_case.RefreshTokenUseCase
import com.example.subreddit_app.data.use_case.SaveTokenUseCase
import com.example.subreddit_app.data.use_case.SearchSubredditUseCase
import com.example.subreddit_app.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RedditViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideRedditUseCase(repository: Repository, tokenManager: TokenManager): RedditUseCase {
        return RedditUseCase(
            refreshTokenUseCase = RefreshTokenUseCase(repository),
            getPopularSubredditUseCase = GetPopularSubredditUseCase(repository),
            clearTokenUseCase = ClearTokenUseCase(tokenManager),
            getSavedTokenUseCase = GetSavedTokenUseCase(tokenManager),
            saveTokenUseCase = SaveTokenUseCase(tokenManager),
            searchSubredditUseCase = SearchSubredditUseCase(repository),
            getSubredditDetailsUseCase = GetSubredditDetailsUseCase(repository),
            getSubredditInfoUseCase = GetSubredditInfoUseCase(repository)
        )
    }
}