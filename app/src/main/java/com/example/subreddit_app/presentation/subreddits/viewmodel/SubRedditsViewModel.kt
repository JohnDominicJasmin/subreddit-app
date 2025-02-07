package com.example.subreddit_app.presentation.subreddits.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.subreddit_app.data.use_case.RedditUseCase
import com.example.subreddit_app.data.use_case.RefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubRedditsViewModel @Inject constructor(
    private val redditUseCase: RedditUseCase
): ViewModel(){
    init {
        viewModelScope.launch {
            redditUseCase.refreshTokenUseCase()

        }
    }
}