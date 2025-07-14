package com.burixer85.aniclips.view.main.home

import android.widget.VideoView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.burixer85.aniclips.R
import com.burixer85.aniclips.domain.model.main.home.Clip
import com.burixer85.aniclips.ui.theme.AniClipsBlue
import com.burixer85.aniclips.ui.theme.TextGray
import com.burixer85.aniclips.view.core.components.AniAndroidView
import com.burixer85.aniclips.view.core.components.AniCircularProgressIndicator
import com.burixer85.aniclips.view.core.components.AniInteractionIconButton


@Composable
fun ClipItem(clip: Clip) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 6.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = clip.user.avatar.avatarUrl,
                contentDescription = "Perfil",
                modifier = Modifier
                    .size(42.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = clip.user.username,
                color = Color.White,
                fontSize = 16.sp,
                maxLines = 1,
                modifier = Modifier.padding(start = 8.dp, end = 12.dp)
            )
            Button(
                onClick = { /* seguir */ },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = AniClipsBlue),
                modifier = Modifier.width(97.dp)
            ) {
                Text("Seguir", fontSize = 12.sp, color = Color.White)
            }
        }

        // Clip
        var hasStarted by remember { mutableStateOf(false) }
        var isPlaying by remember { mutableStateOf(false) }
        var isLoading by remember { mutableStateOf(false) }
        var videoViewRef by remember { mutableStateOf<VideoView?>(null) }
        var isMuted by remember { mutableStateOf(false) }
        var mediaPlayerRef by remember { mutableStateOf<android.media.MediaPlayer?>(null) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {
            if (hasStarted) {
                AniAndroidView(
                    url = clip.videoUrl,
                    isPlaying = isPlaying,
                    onVideoViewReady = { videoView ->
                        videoViewRef = videoView
                        isLoading = true
                        videoView.setOnPreparedListener { mediaPlayer ->
                            isLoading = false
                            mediaPlayer.setVolume(if (isMuted) 0f else 1f, if (isMuted) 0f else 1f)
                            mediaPlayerRef = mediaPlayer
                            if (isPlaying) videoView.start()
                        }
                        videoView.setOnCompletionListener {
                            isPlaying = false
                            hasStarted = false
                        }
                    },
                    modifier = Modifier.matchParentSize()
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable {
                            isPlaying = !isPlaying
                        }
                )

                // Icono de sonido
                IconButton(
                    onClick = {
                        isMuted = !isMuted
                        mediaPlayerRef?.setVolume(if (isMuted) 0f else 1f, if (isMuted) 0f else 1f)
                    },
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isMuted) R.drawable.volumeoff else R.drawable.volumeon
                        ),
                        contentDescription = if (isMuted) "Sonido Off" else "Sonido On",
                        tint = Color.White
                    )
                }
                if (!isPlaying && !isLoading) {
                    IconButton(
                        onClick = { isPlaying = true },
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.play_clip),
                            contentDescription = "Play",
                            tint = Color.White
                        )
                    }
                }
                if (isLoading) {
                    AniCircularProgressIndicator()
                }
            } else {
                AsyncImage(
                    model = clip.thumbnailUrl,
                    contentDescription = "Miniatura",
                    modifier = Modifier.matchParentSize()
                )
                IconButton(
                    onClick = {
                        hasStarted = true
                        isPlaying = true
                        isLoading = true
                    },
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.Center)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.play_clip),
                        contentDescription = "Play",
                        tint = Color.White
                    )
                }
            }
        }

        // Interacciones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AniInteractionIconButton(
                onClick = {},
                painter = painterResource(id = R.drawable.ic_likes),
                contentDescription = "Like",
                text = clip.likes.toString()
            )

            AniInteractionIconButton(
                onClick = {},
                painter = painterResource(id = R.drawable.ic_comments),
                contentDescription = "Comentar",
                text = clip.comments.toString(),
            )

            AniInteractionIconButton(
                onClick = {},
                painter = painterResource(id = R.drawable.ic_rating),
                contentDescription = "Rating",
                text = clip.ratingsCount.toString()
            )
        }

        // Descripción y fecha
        Text(
            text = clip.description,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 18.dp)
                .padding(horizontal = 16.dp)
        )
        Text(
            text = clip.date,
            color = TextGray,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
    }
}