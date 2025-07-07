package com.burixer85.aniclips.view.auth.activateAccount

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.burixer85.aniclips.R
import com.burixer85.aniclips.view.core.components.AniButton
import com.burixer85.aniclips.view.core.components.AniImageLogo
import com.burixer85.aniclips.view.core.components.AniTextField
import kotlinx.coroutines.delay

@Composable
fun ActivateAccountScreen(activateAccountViewModel: ActivateAccountViewModel = hiltViewModel()) {

    val uiState by activateAccountViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var showExitPrompt by remember { mutableStateOf(false) }

    BackHandler {
        if (showExitPrompt) {
            (context as? Activity)?.finish()
        } else {
            showExitPrompt = true
            Toast.makeText(
                context,
                "¿Quieres salir de la app? Pulsa de nuevo para salir.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    if (showExitPrompt) {
        LaunchedEffect(Unit) {
            delay(3000)
            showExitPrompt = false
        }
    }

    LaunchedEffect(Unit) {
        activateAccountViewModel.eventFlow.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold { padding ->
        ConstraintLayout(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp)
                .fillMaxSize(),
        ) {
            val guidelineTop = createGuidelineFromTop(0.2f)
            val guidelineStart = createGuidelineFromStart(0.06f)
            val guidelineEnd = createGuidelineFromEnd(0.06f)

            val (iLogo, tPresentation, tfCode, btnSend) = createRefs()

            AniImageLogo(
                modifier = Modifier
                    .size(180.dp)
                    .constrainAs(iLogo) {
                        end.linkTo(guidelineEnd)
                        start.linkTo(guidelineStart)
                        top.linkTo(guidelineTop)
                    }
            )
            Text(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .constrainAs(tPresentation) {
                        end.linkTo(guidelineEnd)
                        start.linkTo(guidelineStart)
                        top.linkTo(iLogo.bottom)
                        width = Dimension.fillToConstraints

                    },
                text = stringResource(R.string.activate_account_screen_text_presentation),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            AniTextField(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .height(62.dp)
                    .constrainAs(tfCode) {
                        end.linkTo(guidelineEnd)
                        start.linkTo(guidelineStart)
                        top.linkTo(tPresentation.bottom)
                        width = Dimension.fillToConstraints

                    },
                value = uiState.code,
                onValueChange = { activateAccountViewModel.onCodeChanged(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                text = stringResource(R.string.activate_account_screen_textfield_username),
                painter = painterResource(R.drawable.ic_key),
                contentDescription = "Código de activación"
            )
            AniButton(
                modifier = Modifier
                    .padding(top = 34.dp)
                    .height(52.dp)
                    .width(320.dp)
                    .constrainAs(btnSend) {
                        end.linkTo(guidelineEnd)
                        start.linkTo(guidelineStart)
                        top.linkTo(tfCode.bottom)
                    },
                text = stringResource(R.string.activate_account_screen_button_send),
                onClick = { activateAccountViewModel.onClickSelected() }
            )
        }
    }
}