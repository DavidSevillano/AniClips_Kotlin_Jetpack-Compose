package com.burixer85.aniclips.view.core.components

import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun AniAndroidView(
    url: String,
    isPlaying: Boolean,
    onVideoViewReady: (VideoView) -> Unit,
    modifier: Modifier = Modifier
) {
    var videoView: VideoView? by remember { mutableStateOf(null) }

    AndroidView(
        factory = { context ->
            VideoView(context).apply {
                setVideoPath(url)
                setOnPreparedListener { if (isPlaying) start() }
                onVideoViewReady(this)
                videoView = this
            }
        },
        update = { view ->
            if (isPlaying) view.start() else view.pause()
        },
        modifier = modifier
    )
}