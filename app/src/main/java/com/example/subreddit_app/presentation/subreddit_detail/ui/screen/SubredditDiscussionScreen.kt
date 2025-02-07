package com.example.subreddit_app.presentation.subreddit_detail.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.example.subreddit_app.R
import com.example.subreddit_app.data.data_source.local.NetworkConnectivity
import com.example.subreddit_app.domain.model.subreddit_details.SubredditDetails
import com.example.subreddit_app.domain.model.subreddit_details.SubredditDetailsModel
import com.example.subreddit_app.domain.model.subreddit_details.SubredditInfoModel
import com.example.subreddit_app.presentation.search_subreddits.ui.component.RedditPostCard
import com.example.subreddit_app.presentation.search_subreddits.ui.component.SearchSubredditItem
import com.example.subreddit_app.presentation.subreddit_detail.SubredditDetailViewModel
import com.example.subreddit_app.presentation.subreddit_detail.ui.components.SubredditHeader
import com.example.subreddit_app.presentation.subreddit_detail.ui.event.SubredditDetailEvent
import com.example.subreddit_app.presentation.subreddit_detail.ui.state.SubredditDetailUiState
import com.example.subreddit_app.ui.navigation.Screens
import com.example.subreddit_app.ui.theme.CodingChallengeTheme

@Composable
fun SubredditDiscussion(
    viewModel: SubredditDetailViewModel = hiltViewModel(),
    navController: NavController,
    subredditName: String) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.accessToken) {
        viewModel.onEvent(SubredditDetailEvent.GetSubredditDetails(subredditName))
        viewModel.onEvent(SubredditDetailEvent.GetSubredditInfo(subredditName))
    }

    val onClickPost = remember {
        { subredditUrl: String ->
            navController.navigate(Screens.WebViewScreen.passArguments(subredditUrl))

        }
    }
    SubredditDiscussionContent(uiState = uiState, onClickPost = onClickPost)

}


@Composable
fun SubredditDiscussionContent(
    modifier: Modifier = Modifier,
    uiState: SubredditDetailUiState,
    onClickPost: (String) -> Unit
) {
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

        uiState.subredditInfoModel != null -> {
            val info = uiState.subredditInfoModel

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(info.mobileBannerImage)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Banner Image",
                    contentScale = ContentScale.FillHeight,
                    error = painterResource(R.drawable.ic_placeholder),
                    placeholder = painterResource(R.drawable.ic_placeholder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                )

                SubredditHeader(
                    subredditIcon = info.communityIcon,
                    subredditName = info.displayNamePrefixed,
                    membersCount = info.subscribers,
                    onlineCount = info.activeUserCount
                )

                when {
                    uiState.subredditDetailsModel?.discussions != null -> {
                        val searchModel = uiState.subredditDetailsModel.discussions

                        LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
                            itemsIndexed(
                                items = searchModel,
                                key = { _, item -> item.id }
                            ) { _, subreddit ->
                                RedditPostCard(
                                    postImageUrl = subreddit.thumbnail,
                                    username = subreddit.authorFullName,
                                    timeAgo = subreddit.timeAgo,
                                    postText = subreddit.title,
                                    onClickPost = { onClickPost(subreddit.url) }

                                )
                            }
                        }
                    }


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
                androidx.compose.material3.Text(
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
                    text = uiState.error,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSubredditDiscussion() {
    CodingChallengeTheme {
        val sampleSubredditInfo = SubredditInfoModel(
            displayNamePrefixed = "r/Motorcycles",
            mobileBannerImage = "https://styles.redditmedia.com/t5_2qi6d/banner",
            title = "Motorcycles",
            subscribers = "3,796,567",
            url = "https://www.reddit.com/r/Motorcycles/",
            communityIcon = "https://styles.redditmedia.com/t5_2qi6d/icon",
            id = "t5_2qi6d",
            activeUserCount = "22"
        )
        val sampleSubredditDetails = listOf(
            SubredditDetails(
                subredditName = "Motorcycles",
                authorFullName = "t2_q1dkf8pwg",
                title = "Tinnitus Awareness Week",
                subredditNamePrefixed = "r/Motorcycles",
                thumbnail = "https://a.thumbs.redditmedia.com/F4epM2wd-nLiSH1n1tJQ9lxpFVo4MC0RxmNnf6uo018.jpg",
                id = "1ilhhda",
                thumbnailWidth = 140,
                thumbnailHeight = 140,
                timeAgo = "22 hours ago",
                url = ""
            ),
            SubredditDetails(
                subredditName = "Motorcycles",
                authorFullName = "t2_xk3ld9ab",
                title = "Best Beginner Motorcycles",
                subredditNamePrefixed = "r/Motorcycles",
                thumbnail = "https://b.thumbs.redditmedia.com/example.jpg",
                id = "1ilhhdb",
                thumbnailWidth = 140,
                thumbnailHeight = 140,
                timeAgo = "22 hours ago",
                url = ""


            )
        )


        SubredditDiscussionContent(
            uiState = SubredditDetailUiState(
                subredditInfoModel = sampleSubredditInfo,
                subredditDetailsModel = SubredditDetailsModel(sampleSubredditDetails)),
            onClickPost = {})
    }
}