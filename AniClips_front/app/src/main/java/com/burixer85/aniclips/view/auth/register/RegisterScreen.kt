package com.burixer85.aniclips.view.auth.register

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.burixer85.aniclips.R
import com.burixer85.aniclips.view.core.components.AniButton
import com.burixer85.aniclips.view.core.components.AniCircularProgressIndicator
import com.burixer85.aniclips.view.core.components.AniImageLogo
import com.burixer85.aniclips.view.core.components.AniTextField
import com.burixer85.aniclips.view.core.components.AniTextFieldPassword

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    navigateToActivateAccount: () -> Unit,
    navigateBack: () -> Unit
) {
    val uiState by registerViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        registerViewModel.eventFlow.collect { message ->
            if (message == "Registro exitoso") {
                navigateToActivateAccount()
            } else if (message.isNotBlank()) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val (ilogo, tPresentation, tfEmail, tfUsername, tfPassword, tfRepeatPassword, btnRegister, tbtnBackToLogin) = createRefs()
            var passwordHidden by remember { mutableStateOf(true) }
            var repeatPasswordHidden by remember { mutableStateOf(true) }

            AniImageLogo(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .size(180.dp)
                    .constrainAs(ilogo) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    },
            )
            Text(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .constrainAs(tPresentation) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(ilogo.bottom)
                    },
                text = stringResource(R.string.register_screen_text_presentation),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            AniTextField(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .height(62.dp)
                    .width(320.dp)
                    .constrainAs(tfEmail) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tPresentation.bottom)
                    },
                value = uiState.email,
                onValueChange = { registerViewModel.onEmailChanged(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                text = stringResource(R.string.register_screen_textfield_email),
                painter = painterResource(R.drawable.ic_mail),
                contentDescription = "Email"
            )
            AniTextField(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .height(62.dp)
                    .width(320.dp)
                    .constrainAs(tfUsername) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tfEmail.bottom)
                    },
                value = uiState.username,
                onValueChange = { registerViewModel.onUsernameChanged(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                text = stringResource(R.string.register_screen_textfield_username),
                painter = painterResource(R.drawable.face_24px),
                contentDescription = "Nombre de usuario"
            )
            AniTextFieldPassword(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .height(62.dp)
                    .width(320.dp)
                    .constrainAs(tfPassword) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tfUsername.bottom)
                    },
                value = uiState.password,
                onValueChange = { registerViewModel.onPasswordChanged(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                text = stringResource(R.string.register_screen_textfield_password),
                painter = painterResource(R.drawable.lock_24px),
                contentDescription = "Contraseña",
                passwordHidden = passwordHidden,
                onPasswordVisibilityChange = { passwordHidden = !passwordHidden }
            )
            AniTextFieldPassword(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .height(62.dp)
                    .width(320.dp)
                    .constrainAs(tfRepeatPassword) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tfPassword.bottom)
                    },
                value = uiState.repeatPassword,
                onValueChange = { registerViewModel.onRepeatPasswordChanged(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                text = stringResource(R.string.register_screen_textfield_repeat_password),
                painter = painterResource(R.drawable.lock_24px),
                contentDescription = "Repite la contraseña",
                passwordHidden = repeatPasswordHidden,
                onPasswordVisibilityChange = { repeatPasswordHidden = !repeatPasswordHidden }
            )
            AniButton(
                modifier = Modifier
                    .padding(top = 36.dp)
                    .height(52.dp)
                    .width(320.dp)
                    .constrainAs(btnRegister) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tfRepeatPassword.bottom)
                    },
                text = stringResource(R.string.register_screen_button_register),
                onClick = { registerViewModel.onClickSelected() },
            )
            TextButton(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .constrainAs(tbtnBackToLogin) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(btnRegister.bottom)
                    },
                onClick = { navigateBack() }) {
                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.register_screen_text_go_back_login_question) + " ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append(stringResource(R.string.register_screen_text_go_back_login_blue))
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        if (uiState.isLoading) {
            AniCircularProgressIndicator()
        }
    }
}