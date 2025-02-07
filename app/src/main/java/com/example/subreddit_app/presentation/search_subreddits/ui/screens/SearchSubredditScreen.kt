package com.example.subreddit_app.presentation.search_subreddits.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.subreddit_app.data.data_source.local.NetworkConnectivity
import com.example.subreddit_app.domain.model.search.SearchItem
import com.example.subreddit_app.domain.model.search.SubredditSearchModel
import com.example.subreddit_app.presentation.search_subreddits.SearchSubredditViewModel
import com.example.subreddit_app.presentation.search_subreddits.ui.component.SearchBar
import com.example.subreddit_app.presentation.search_subreddits.ui.component.SearchSubredditItem
import com.example.subreddit_app.presentation.search_subreddits.ui.event.SearchSubredditEvent
import com.example.subreddit_app.presentation.search_subreddits.ui.state.SearchSubredditUiState
import com.example.subreddit_app.ui.navigation.Screens
import com.example.subreddit_app.ui.navigation.navigateScreen
import com.example.subreddit_app.ui.theme.CodingChallengeTheme

@Composable
fun SearchSubredditScreen(
    viewModel: SearchSubredditViewModel = hiltViewModel(),
    navController: NavController) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }

    SearchSubredditContent(
        modifier = Modifier,
        query = searchQuery,
        onQueryChange = { searchQuery = it },
        onSearch = {
            viewModel.onEvent(SearchSubredditEvent.SearchSubreddit(it))
        },

        uiState = uiState,
        onItemClick = { item ->
            navController.navigateScreen(Screens.RedditDetails.passArguments(item.displayName))

        })

}

@Composable
fun SearchSubredditContent(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onItemClick: (SearchItem) -> Unit,
    uiState: SearchSubredditUiState
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = onSearch
            )

            Text(
                text = "Communities",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 12.dp)
            )


            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.Red)
                    }
                }

                uiState.subredditSearchModel != null -> {
                    val searchModel = uiState.subredditSearchModel
                    LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
                        itemsIndexed(
                            items = searchModel.searches,
                            key = { index, item -> item.id }
                        ) { index, subreddit ->
                            SearchSubredditItem(item = subreddit, onItemClick = onItemClick)
                        }
                    }

                }

                !NetworkConnectivity(context).hasInternet() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = "No Internet Connection",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }

                }


                uiState.error != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = uiState.error,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }
                }

            }


        }
    }

}


@Preview
@Composable
private fun PreviewSearchSubreddit() {
    CodingChallengeTheme {
        val mockSearchItems = listOf(
            SearchItem(
                displayName = "gaming",
                headerImage = "https://www.example.com/header_gaming.jpg",
                title = "Gaming",
                displayNamePrefixed = "r/gaming",
                subscribers = "3.5M",
                id = "gaming123",
                publicDescription = "A place for gaming enthusiasts.",
                communityIcon = "https://www.example.com/icon_gaming.png",
                description = "All things gaming, from retro to next-gen.",
                url = "/r/gaming",
                headerTitle = "Gaming Community"
            ),
            SearchItem(
                displayName = "technology",
                headerImage = "https://www.example.com/header_tech.jpg",
                title = "Technology",
                displayNamePrefixed = "r/technology",
                subscribers = "2.1M",
                id = "tech456",
                publicDescription = "The latest updates on tech and innovation.",
                communityIcon = "https://www.example.com/icon_tech.png",
                description = "A place to discuss all things technology-related.",
                url = "/r/technology",
                headerTitle = "Tech News & Discussions"
            ),
            SearchItem(
                displayName = "movies",
                headerImage = "https://www.example.com/header_movies.jpg",
                title = "Movies",
                displayNamePrefixed = "r/movies",
                subscribers = "4.8M",
                id = "movies789",
                publicDescription = "Talk about your favorite films and reviews.",
                communityIcon = "https://www.example.com/icon_movies.png",
                description = "From Hollywood to Indie films, discuss it all.",
                url = "/r/movies",
                headerTitle = "Movie Lovers"
            ),
            SearchItem(
                displayName = "science",
                headerImage = "https://www.example.com/header_science.jpg",
                title = "Science",
                displayNamePrefixed = "r/science",
                subscribers = "6.2M",
                id = "science101",
                publicDescription = "Explore the wonders of science and research.",
                communityIcon = "https://www.example.com/icon_science.png",
                description = "A subreddit for scientific discussions and discoveries.",
                url = "/r/science",
                headerTitle = "Scientific Breakthroughs"
            ),
            SearchItem(
                displayName = "fitness",
                headerImage = "https://www.example.com/header_fitness.jpg",
                title = "Fitness",
                displayNamePrefixed = "r/fitness",
                subscribers = "1.9M",
                id = "fitness202",
                publicDescription = "Tips, advice, and discussions on staying fit.",
                communityIcon = "https://www.example.com/icon_fitness.png",
                description = "Everything about fitness, from workouts to diets.",
                url = "/r/fitness",
                headerTitle = "Health & Fitness"
            )
        )
        val mock = SubredditSearchModel(searches = mockSearchItems)
        var searchQuery by remember { mutableStateOf("asd") }

        SearchSubredditContent(
            uiState = SearchSubredditUiState(subredditSearchModel = mock),
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = {

            }, onItemClick = {

            })

    }
}