package com.burixer85.aniclips.view.core.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.burixer85.aniclips.R

@Composable
fun AniImageLogo(
    modifier: Modifier,
    painter: Painter = painterResource(R.drawable.logo),
    contentDescription: String = "Logo"
) {
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription
    )
}