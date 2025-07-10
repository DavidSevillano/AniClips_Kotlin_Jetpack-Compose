package com.burixer85.aniclips.view.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.burixer85.aniclips.R


@Composable
fun AniDialogActivateAccount(
    username: String,
    onAccept: () -> Unit,
) {
    Dialog(onDismissRequest = {}) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(21.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.account_activated))
                LottieAnimation(
                    composition = composition,
                    iterations = 1,
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "¡Bienvenido $username!",
                    color = colorResource(id = R.color.white),
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Ya puedes iniciar sesión con tu cuenta",
                    color = colorResource(id = R.color.white),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(30.dp))
                AniButton(
                    onClick = onAccept,
                    modifier = Modifier
                        .width(300.dp)
                        .height(62.dp),
                    text = "Aceptar"

                )
            }
        }
    }
}
