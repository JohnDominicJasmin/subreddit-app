package com.example.subreddit_app.presentation.subreddits.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.subreddit_app.R
import com.example.subreddit_app.data.data_source.local.NetworkConnectivity
import com.example.subreddit_app.domain.model.popular_subreddit.PopularSubredditItem
import com.example.subreddit_app.domain.model.popular_subreddit.PopularSubredditsModel
import com.example.subreddit_app.presentation.subreddits.ui.component.PopularSubredditItem
import com.example.subreddit_app.presentation.subreddits.SubRedditsViewModel
import com.example.subreddit_app.ui.navigation.Screens
import com.example.subreddit_app.ui.navigation.navigateScreen
import com.example.subreddit_app.ui.theme.Blue20

@Composable
fun SubRedditsScreen(
    viewModel: SubRedditsViewModel = hiltViewModel(),
    navController: NavController,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
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

        uiState.popularSubredditsModel != null -> {
            val popularSubreddits = uiState.popularSubredditsModel

            SubRedditsContent(
                popularSubreddits = popularSubreddits,
                onClickSearch = { navController.navigateScreen(Screens.SearchSubreddits.route) },
                onItemClick = { item ->
                    navController.navigateScreen(Screens.RedditDetails.passArguments(item.displayName))
                }
            )
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
                    text = uiState.error!!,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun SubRedditsContent(
    modifier: Modifier = Modifier,
    popularSubreddits: PopularSubredditsModel?,
    onClickSearch: () -> Unit = {},
    onItemClick: (PopularSubredditItem) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(all = 6.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.reddit_svgrepo_com),
                    contentDescription = "Reddit Logo",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )

                ClickableSearchField(onClick = onClickSearch)
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Popular Subreddits",
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = "Browse popular subreddits",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
                if (popularSubreddits != null) {
                    itemsIndexed(
                        items = popularSubreddits.popularSubredditItems,
                        key = { index, item -> item.id }
                    ) { index, subreddit ->
                        PopularSubredditItem(
                            number = (index + 1).toString(),
                            item = subreddit,
                            onItemClick = onItemClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ClickableSearchField(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Blue20, shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Search here...",
                color = Color.DarkGray,
                fontSize = 16.sp
            )
        }
    }
}


@Preview
@Composable
private fun PreviewSubRedditsScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        val mock = PopularSubredditsModel(popularSubredditItems = listOf(
            com.example.subreddit_app.domain.model.popular_subreddit.PopularSubredditItem(
                primaryColor = "#FF4500",
                iconImage = "https://www.redditstatic.com/gold/awards/icon/silver_512.png",
                advertiserCategory = "r/aww",
                publicDescription = "A subreddit for cute and cuddly pictures",
                title = "Aww",

                bannerBackgroundImage = "https://styles.redditmedia.com/t5_2qh1o/styles/bannerBackgroundImage_4g5v5z9v7c751.jpg",
                url = "/r/aww/",
                subscribers = "24.5M",
                displayNamePrefixed = "r/aww",
                id = "99",
                displayName = "aww"

            )
        ))
        SubRedditsContent(popularSubreddits = mock, onItemClick = {})
    }
}