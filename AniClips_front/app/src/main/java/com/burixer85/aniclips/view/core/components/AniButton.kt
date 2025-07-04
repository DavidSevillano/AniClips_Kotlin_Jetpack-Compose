package com.burixer85.aniclips.view.core.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun AniButton(
    modifier: Modifier,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ),
    onClick: () -> Unit,
    shape: Shape = MaterialTheme.shapes.medium,
    elevation: ButtonElevation? = null
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        shape = shape,
        colors = colors,
        elevation = elevation,
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}