package com.burixer85.aniclips.view.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.burixer85.aniclips.ui.theme.shapes

@Composable
fun AniProfileInformation(modifier: Modifier = Modifier, infoName: String, number: Int) {
    Box(
        modifier = modifier
            .height(80.dp)
            .clip(shapes.extraLarge)
            .background(MaterialTheme.colorScheme.onBackground),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                modifier = Modifier.padding(bottom = 3.dp),
                text = infoName,
                color = Color.White,
                fontSize = 13.sp
            )
            Text(text = number.toString(), color = Color.White, fontSize = 24.sp)
        }

    }
}