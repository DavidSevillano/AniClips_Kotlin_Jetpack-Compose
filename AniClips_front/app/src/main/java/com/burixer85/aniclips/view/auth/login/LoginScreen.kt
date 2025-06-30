package com.burixer85.aniclips.view.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.burixer85.aniclips.R

@Preview
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {

    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { padding ->
        ConstraintLayout(
            Modifier
                .background(Color(0xFF191A1F))
                .padding(24.dp)
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
        ) {
            val (iLogo, tPresentation, tfEmail, tfPassword, btnLogin, tbtnNotAccount, tbtnRegister) = createRefs()
            var passwordHidden by remember { mutableStateOf(true) }
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
                text = "Inicia sesión con tu cuenta",
                fontSize = 24.sp,
                color = Color.White
            )
            TextField(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth()
                    .constrainAs(tfEmail) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tPresentation.bottom)
                    },
                value = uiState.email,
                onValueChange = { loginViewModel.onEmailChanged(it) },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFF1F222B),
                    focusedContainerColor = Color(0xFF1F222B),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                shape = MaterialTheme.shapes.small,
                placeholder = {
                    Text(text = "Nombre de usuario", color = Color(0xFF66FFFFFF))
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.face_24px),
                        contentDescription = "Nombre de usuario"
                    )
                }
            )
            TextField(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth()
                    .constrainAs(tfPassword) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tfEmail.bottom)
                    },
                value = uiState.password,
                onValueChange = { loginViewModel.onPasswordChanged(it) },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFF1F222B),
                    focusedContainerColor = Color(0xFF1F222B),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                shape = MaterialTheme.shapes.small,
                placeholder = {
                    Text(text = "Contraseña", color = Color(0xFF66FFFFFF))
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.lock_24px),
                        contentDescription = "Nombre de usuario"
                    )
                },
                visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable { passwordHidden = !passwordHidden },
                        painter = painterResource(
                            if (passwordHidden) R.drawable.visibility_off else R.drawable.visibility_on
                        ),
                        tint = Color.White,
                        contentDescription = if (passwordHidden) "Mostrar contraseña" else "Ocultar contraseña"
                    )
                },
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(top = 30.dp)
                    .constrainAs(btnLogin) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tfPassword.bottom)
                    },
                onClick = {},
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2D54CB),
                    contentColor = Color.White
                )
            ) { Text(text = "iniciar Sesión", fontSize = 16.sp) }
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
                    text = "Acceso sin cuenta registrada",
                    fontSize = 16.sp,
                    color = Color(0xFF2D54CB)
                )
            }
            TextButton(
                modifier = Modifier
                    .padding(top = 62.dp)
                    .constrainAs(tbtnRegister) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tbtnNotAccount.bottom)
                    },
                onClick = {}) {
                Text(
                    buildAnnotatedString {
                        append("¿No tienes cuenta? ")
                        withStyle(style = SpanStyle(color = Color(0xFF2D54CB))) {
                            append("Regístrate")
                        }
                    },
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }

    }
}