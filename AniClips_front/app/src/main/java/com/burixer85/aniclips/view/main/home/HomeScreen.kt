package com.burixer85.aniclips.view.main.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val uiState by homeScreenViewModel.uiState.collectAsStateWithLifecycle()
    val clips = homeScreenViewModel.clips.collectAsState()

    /*
    val clip = Clip(
        id = 1,
        username = "UsuarioEjemplo",
        description = "Descripción de prueba para el clip.",
        date = "10 de febrero",
        likeCount = 123,
        commentCount = 45,
        rating = 4.8,
        isFollowed = false,
        isLoading = false,
        videoUrl = "https://ejemplo.com/video.mp4"
    )
     */

    LaunchedEffect(Unit) {
        homeScreenViewModel.loadClips()
    }

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        /*
        Column(modifier = Modifier.padding(padding)) {
            ClipItem(clip)
        }
         */

        LazyColumn(contentPadding = padding) {
            items(clips.value) { clip ->
                ClipItem(clip)
            }
        }
    }


}