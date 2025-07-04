package com.burixer85.aniclips.view.core.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import com.burixer85.aniclips.ui.theme.TextFieldPlaceholder

@Composable
fun AniTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    colors: TextFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
        focusedContainerColor = MaterialTheme.colorScheme.onBackground,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
    ),
    keyboardOptions: KeyboardOptions,
    singleLine: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    text: String,
    color: Color = TextFieldPlaceholder,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    painter: Painter,
    contentDescription: String,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        colors = colors,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        shape = shape,
        placeholder = {
            Text(
                text = text,
                color = color,
                style = style
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.alpha(0.8f),
                painter = painter,
                contentDescription = contentDescription
            )
        },
    )
}