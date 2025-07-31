package com.burixer85.aniclips.view.main.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.burixer85.aniclips.ui.theme.TextFieldPlaceholder
import com.burixer85.aniclips.view.core.components.AniCircularProgressIndicator

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
) {

    val uiState by searchScreenViewModel.uiState.collectAsStateWithLifecycle()
    val thumbnails = searchScreenViewModel.thumbnails.collectAsState()
    val gridState = rememberLazyGridState()


    LaunchedEffect(Unit) {
        searchScreenViewModel.loadThumbnails()
    }

    LaunchedEffect(gridState, uiState) {
        snapshotFlow { gridState.layoutInfo }
            .collect { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                if (totalItems > 0 && lastVisibleItem >= totalItems - 3 && !uiState.isLoading && !uiState.isPaging) {
                    searchScreenViewModel.loadNextPage()
                }
            }
    }


    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        Column(modifier = Modifier.padding(padding)) {
            TextField(
                value = uiState.search,
                onValueChange = { searchScreenViewModel.onSearchChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(top = 28.dp, start = 18.dp, end = 18.dp),
                placeholder = {
                    Text(
                        text = "Nombre de anime",
                        color = TextFieldPlaceholder,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                    focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                ),
                shape = MaterialTheme.shapes.medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                },
                trailingIcon = {
                    Button(
                        modifier = Modifier.padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ),
                        onClick = {},
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Text(
                            text = "Filtros",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            )

            LazyVerticalGrid(
                modifier = Modifier.padding(top = 18.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
                columns = GridCells.Fixed(3),
                state = gridState
            ) {
                items(thumbnails.value) { thumbnail ->
                    ThumbnailItem(thumbnail)
                }
            }
        }
        if (uiState.isLoading && thumbnails.value.isEmpty()) {
            AniCircularProgressIndicator()
        }
    }
}
