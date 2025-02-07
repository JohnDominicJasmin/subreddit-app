package com.example.subreddit_app.presentation.search_subreddits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.subreddit_app.data.use_case.RedditUseCase
import com.example.subreddit_app.presentation.search_subreddits.ui.event.SearchSubredditEvent
import com.example.subreddit_app.presentation.search_subreddits.ui.state.SearchSubredditUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchSubredditViewModel @Inject constructor(
    private val redditUseCase: RedditUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchSubredditUiState())
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


    fun onEvent(event: SearchSubredditEvent){
        when(event){
            is SearchSubredditEvent.SearchSubreddit -> {
                searchSubreddits(event.query)

            }
        }
    }


    private fun searchSubreddits( query: String) {
        val accessToken = state.value.accessToken
        if(accessToken == null){
            _state.update { it.copy(error = "No token found") }
            return;
        }

        viewModelScope.launch {
            runCatching {
                _state.update { it.copy(isLoading = true) }
                redditUseCase.searchSubredditUseCase(accessToken, query)

            }.onSuccess { result ->
                _state.update { it.copy(subredditSearchModel = result, isLoading = false, error = null) }
            }.onFailure { error ->
                _state.update { it.copy(error = error.message, isLoading = false) }
            }
        }
    }

}