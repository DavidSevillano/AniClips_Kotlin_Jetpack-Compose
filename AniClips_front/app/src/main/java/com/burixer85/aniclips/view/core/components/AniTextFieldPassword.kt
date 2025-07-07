package com.burixer85.aniclips.view.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.burixer85.aniclips.R
import com.burixer85.aniclips.ui.theme.TextFieldPlaceholder

@Composable
fun AniTextFieldPassword(
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
    passwordHidden: Boolean,
    onPasswordVisibilityChange: () -> Unit
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        colors = colors,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        shape = shape,
        textStyle = style,
        placeholder = {
            Text(
                text = text,
                color = color,
                style = style
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .alpha(0.8f)
                    .padding(start = 12.dp),
                painter = painter,
                contentDescription = contentDescription
            )
        },
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .alpha(0.8f)
                    .padding(end = 12.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onPasswordVisibilityChange() },
                painter = painterResource(
                    if (passwordHidden) R.drawable.visibility_off else R.drawable.visibility_on
                ),
                contentDescription = if (passwordHidden) "Mostrar contraseña" else "Ocultar contraseña"
            )
        },
    )
}