package com.example.subreddit_app.presentation.subreddits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.subreddit_app.data.use_case.RedditUseCase
import com.example.subreddit_app.presentation.subreddits.ui.state.SubRedditUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SubRedditsViewModel @Inject constructor(
    private val redditUseCase: RedditUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SubRedditUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                _state.update { it.copy(isLoading = true) }
                redditUseCase.refreshTokenUseCase()
            }.onSuccess { resultToken ->
                _state.update { it.copy(token = resultToken, isLoading = false) }
                resultToken.accessToken?.let { token ->
                    getPopularSubreddits(token)
                    redditUseCase.saveTokenUseCase(token)

                }
            }.onFailure { error ->
                _state.update { it.copy(error = error.message, isLoading = false) }
            }

        }
    }

    private fun getPopularSubreddits(accessToken: String) {
        viewModelScope.launch {
            runCatching {
                _state.update { it.copy(isLoading = true) }
                redditUseCase.getPopularSubredditUseCase(accessToken)
            }.onSuccess { subreddits ->
                _state.update { it.copy(popularSubredditsModel = subreddits, isLoading = false) }
            }.onFailure { error ->
                _state.update { it.copy(error = error.message, isLoading = false) }
            }
        }
    }
}