package com.burixer85.aniclips.view.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.burixer85.aniclips.R
import com.burixer85.aniclips.ui.theme.AniClipsBlue
import com.burixer85.aniclips.ui.theme.BoxMyClips
import com.burixer85.aniclips.view.core.components.AniProfileInformation
import com.burixer85.aniclips.view.main.search.ThumbnailItem

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        profileScreenViewModel.loadProfile()
    }
    Scaffold(
        modifier = modifier, containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        val uiState = profileScreenViewModel.uiState.collectAsStateWithLifecycle()
        val profileData = profileScreenViewModel.profileData.collectAsStateWithLifecycle().value
        val gridState = rememberLazyGridState()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 26.dp),
                    text = profileData.username,
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(top = 26.dp),
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_logout),
                        contentDescription = "logout",
                        tint = Color.White
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 88.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = profileData.avatar,
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AniProfileInformation(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    infoName = stringResource(R.string.profile_screen_box_clips),
                    number = profileData.clipsCount
                )
                AniProfileInformation(
                    modifier = Modifier.weight(1f),
                    infoName = stringResource(R.string.profile_screen_box_followers),
                    number = profileData.followers
                )
                AniProfileInformation(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    infoName = stringResource(R.string.profile_screen_box_followed),
                    number = profileData.followed
                )
            }
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.profile_screen_text_description_title),
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(165.dp)
                    .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(MaterialTheme.colorScheme.onBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center,
                    text = profileData.description,
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Icon(
                modifier = Modifier
                    .size(40.dp),
                painter = painterResource(R.drawable.ic_myclips),
                tint = AniClipsBlue,
                contentDescription = "My clips icon"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.White)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(BoxMyClips)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    state = gridState,
                ) {
                    items(profileData.clips) { clip ->
                        ThumbnailItem(clip)
                    }
                }
            }
        }
    }

}
