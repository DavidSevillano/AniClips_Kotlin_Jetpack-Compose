package com.burixer85.aniclips.view.auth.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.burixer85.aniclips.R
import com.burixer85.aniclips.view.core.components.AniButton
import com.burixer85.aniclips.view.core.components.AniTextField
import com.burixer85.aniclips.view.core.components.AniTextFieldPassword


@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel(), navigateToRegister: () -> Unit) {

    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        loginViewModel.eventFlow.collect { message ->
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
            val (iLogo, tPresentation, tfUsername, tfPassword, btnLogin, tbtnNotAccount, tbtnRegister) = createRefs()
            var passwordHidden by remember { mutableStateOf(true) }

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .constrainAs(createRef()) {
                            centerTo(parent)
                        }
                )
            }

            Image(
                modifier = Modifier
                    .padding(top = 64.dp)
                    .size(180.dp)
                    .constrainAs(iLogo) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    },
                painter = painterResource(R.drawable.logo),
                contentDescription = "Aniclips logo"
            )
            Text(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .constrainAs(tPresentation) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(iLogo.bottom)
                    },
                text = stringResource(R.string.login_screen_text_presentation),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            AniTextField(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .height(62.dp)
                    .width(320.dp)
                    .constrainAs(tfUsername) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tPresentation.bottom)
                    },
                value = uiState.username,
                onValueChange = { loginViewModel.onUsernameChanged(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                text = stringResource(R.string.login_screen_textfield_username),
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
                onValueChange = { loginViewModel.onPasswordChanged(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                text = stringResource(R.string.login_screen_textfield_password),
                painter = painterResource(R.drawable.lock_24px),
                contentDescription = "Contraseña",
                passwordHidden = passwordHidden,
                onPasswordVisibilityChange = { passwordHidden = !passwordHidden }
            )
            AniButton(
                modifier = Modifier
                    .padding(top = 34.dp)
                    .height(52.dp)
                    .width(320.dp)
                    .constrainAs(btnLogin) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tfPassword.bottom)
                    },
                text = stringResource(R.string.login_screen_button_login),
                onClick = { loginViewModel.onClickSelected() }
            )
            TextButton(
                modifier = Modifier
                    .padding(top = 22.dp)
                    .constrainAs(tbtnNotAccount) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(btnLogin.bottom)
                    },
                onClick = {},
            ) {
                Text(
                    text = stringResource(R.string.login_screen_text_no_account_access),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
            TextButton(
                modifier = Modifier
                    .padding(top = 82.dp)
                    .constrainAs(tbtnRegister) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tbtnNotAccount.bottom)
                    },
                onClick = { navigateToRegister() }) {
                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.login_screen_text_register_question) + " ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append(stringResource(R.string.login_screen_text_register_blue))
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

    }
}