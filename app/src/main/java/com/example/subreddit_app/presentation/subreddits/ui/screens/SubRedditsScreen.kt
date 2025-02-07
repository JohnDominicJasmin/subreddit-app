package com.example.subreddit_app.presentation.subreddits.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.subreddit_app.presentation.subreddits.viewmodel.SubRedditsViewModel

@Composable
fun SubRedditsScreen(
    viewModel: SubRedditsViewModel = hiltViewModel(),
    navController: NavController,
) {


    SubRedditsContent()
}

@Composable
fun SubRedditsContent(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize().background(color = Color.White)){

    }
}

@Preview
@Composable
private fun PreviewSubRedditsScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        SubRedditsContent()
    }
}