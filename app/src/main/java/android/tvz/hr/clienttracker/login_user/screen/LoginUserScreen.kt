package android.tvz.hr.clienttracker.login_user.screen

import android.tvz.hr.clienttracker.R
import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.login_user.domain.LoginFormEvent
import android.tvz.hr.clienttracker.login_user.viewmodel.LoginViewModel
import android.tvz.hr.clienttracker.navigation.Screen
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LoginUserScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<LoginViewModel>()

    val state = viewModel.state
    val myContext = LocalContext.current
    LaunchedEffect(key1 = myContext) {
        viewModel.loginState.collect { result ->
            when (result) {
                is Result.Success -> {
                    Toast.makeText(
                        myContext,
                        myContext.getString(R.string.login_user_is_logged_in),
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
                is Result.Error -> {
                    Toast.makeText(
                        myContext,
                        myContext.getText(R.string.login_there_is_some_problem_with_logging),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Result.Loading -> {}
            }
        }
    }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(id = R.string.login_user),
            fontSize = MaterialTheme.typography.displayMedium.fontSize,
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = state.username,
            onValueChange =
            { username ->
                viewModel.onEvent(LoginFormEvent.UsernameChanged(username))
            },
            label = {
                Text(
                    text = stringResource(id = R.string.register_user_username),
                    color = Color.Black
                )
            },
            visualTransformation = VisualTransformation.None,
            keyboardOptions = KeyboardOptions.Default,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                errorCursorColor = Color.Red,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            isError = state.usernameError != null,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Person icon"
                )
            },
        )
        if (state.usernameError != null) {
            Text(
                text = state.usernameError,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = state.password,
            { password ->
                viewModel.onEvent(LoginFormEvent.PasswordChanged(password))
            },
            label = {
                Text(
                    style = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    text = stringResource(id = R.string.register_user_password),
                    color = Color.Black
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                errorCursorColor = Color.Red,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            isError = state.passwordError != null,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Password icon"
                )
            },

            )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedButton(
            onClick = {
                viewModel.onEvent(LoginFormEvent.Submit)

            },
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.errorContainer),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Text(text = stringResource(id = R.string.login_user))
        }
        Spacer(modifier = Modifier.height(15.dp))

        Text(buildAnnotatedString {
            append("Not registered? ")
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("Register now")
            }
        },
            modifier = Modifier.clickable { navController.navigate(Screen.Register.route) }
        )


    }

}


@Composable
@Preview
fun LoginUserScreenPreview() {
    val myContext = LocalContext.current
    val navController: NavController = NavController(myContext)
    LoginUserScreen(navController = navController)
}