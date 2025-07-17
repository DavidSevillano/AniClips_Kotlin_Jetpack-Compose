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
import com.burixer85.aniclips.view.core.components.AniCircularProgressIndicator

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val uiState by homeScreenViewModel.uiState.collectAsStateWithLifecycle()
    val clips = homeScreenViewModel.clips.collectAsState()

    LaunchedEffect(Unit) {
        homeScreenViewModel.loadClips()
    }

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        LazyColumn(contentPadding = padding) {
            items(clips.value) { clip ->
                ClipItem(clip)
            }
        }
        if (uiState.isLoading) {
            AniCircularProgressIndicator()
        }
    }


}