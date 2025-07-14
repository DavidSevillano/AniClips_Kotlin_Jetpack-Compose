package com.burixer85.aniclips.view.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AniInteractionIconButton(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String,
    text: String
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = Color.White
        )
    }
    Text(
        text = text,
        color = Color.White,
        fontSize = 16.sp,
        modifier = Modifier.padding(end = 16.dp)
    )

}