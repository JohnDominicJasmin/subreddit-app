package com.example.subreddit_app.presentation.subreddit_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.subreddit_app.data.use_case.RedditUseCase
import com.example.subreddit_app.presentation.subreddit_detail.ui.event.SubredditDetailEvent
import com.example.subreddit_app.presentation.subreddit_detail.ui.state.SubredditDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubredditDetailViewModel @Inject constructor(
    private val redditUseCase: RedditUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SubredditDetailUiState())
    val state = _state.asStateFlow()

    init {
        getSavedToken()
    }

    private fun getSavedToken() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            redditUseCase.getSavedTokenUseCase()
                .distinctUntilChanged()
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    _state.update { it.copy(error = error.message, isLoading = false) }
                }
                .collect { token ->
                    _state.update { it.copy(accessToken = token, isLoading = false) }
                }
        }
    }


    fun onEvent(event: SubredditDetailEvent) {
        when (event) {
            is SubredditDetailEvent.GetSubredditDetails -> {
                getSubredditDetails(event.subreddit)
            }

            is SubredditDetailEvent.GetSubredditInfo -> {
                getSubredditInfo(event.subreddit)
            }
        }
    }

    private fun getSubredditInfo(subreddit: String) {
        val accessToken = state.value.accessToken
        if (accessToken == null) {
            _state.update { it.copy(error = "No token found") }
            return;
        }
        viewModelScope.launch {
            runCatching {
                _state.update { it.copy(isLoading = true) }
                redditUseCase.getSubredditInfoUseCase(
                    accessToken = accessToken,
                    subreddit = subreddit)
            }.onSuccess { subredditInfoModel ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        subredditInfoModel = subredditInfoModel)
                }
            }.onFailure {

            }
        }
    }

    private fun getSubredditDetails(subreddit: String) {
        val accessToken = state.value.accessToken
        if (accessToken == null) {
            _state.update { it.copy(error = "No token found") }
            return;
        }

        viewModelScope.launch {
            runCatching {
                _state.update { it.copy(isLoading = true) }
                redditUseCase.getSubredditDetailsUseCase(
                    accessToken = accessToken,
                    subreddit = subreddit)
            }.onSuccess { result ->
                _state.update { it.copy(isLoading = false, subredditDetailsModel = result, error = null) }
            }.onFailure { error ->
                _state.update { it.copy(error = error.message) }
            }
        }
    }

}