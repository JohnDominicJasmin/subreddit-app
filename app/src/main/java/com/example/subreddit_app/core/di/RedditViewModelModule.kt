package com.example.subreddit_app.core.di

import com.example.subreddit_app.data.use_case.RedditUseCase
import com.example.subreddit_app.data.use_case.RefreshTokenUseCase
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
    fun provideRedditUseCase(repository: Repository): RedditUseCase {
        return RedditUseCase(
            refreshTokenUseCase = RefreshTokenUseCase(repository)
        )
    }
}