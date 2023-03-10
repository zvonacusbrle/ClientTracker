package android.tvz.hr.clienttracker.user_registration.screen

import android.tvz.hr.clienttracker.R
import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.navigation.Screen
import android.tvz.hr.clienttracker.user_registration.domain.RegistrationFormEvent
import android.tvz.hr.clienttracker.user_registration.viewmodel.RegistrationViewModel
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun RegisterUserScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<RegistrationViewModel>()

    val state = viewModel.state
    val myContext = LocalContext.current
    LaunchedEffect(key1 = myContext) {
        viewModel.registerState.collect { result ->
            when (result) {
                is Result.Success -> {
                    Toast.makeText(
                        myContext,
                        myContext.getString(R.string.registration_user_successfully_registered),
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                }
                is Result.Error -> {
                    Toast.makeText(
                        myContext,
                        myContext.getText(R.string.registration_user_there_is_some_mistake_with_registration),
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
        IconButton(
            onClick = { navController.navigate(Screen.Login.route) },
            modifier = Modifier
                .align(Alignment.Start)
                .width(40.dp)
                .height(30.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = stringResource(
                    id = R.string.register_user_button_back,
                ),

                )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.register_user_create_account),
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = stringResource(id = R.string.register_user_input_fields_bellow),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = state.username,
            onValueChange =
            {
                viewModel.onEvent(RegistrationFormEvent.UsernameChanged(it))
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

            {
                viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))
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
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = state.repeatedPassword,
            onValueChange =
            {
                viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
            },
            label = {
                Text(
                    text = stringResource(id = R.string.register_user_password_again),
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
            isError = state.repeatedPasswordError != null,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Password icon"
                )
            },

            )

        if (state.repeatedPasswordError != null) {
            Text(
                text = state.repeatedPasswordError,
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedButton(
            onClick = {
                viewModel.onEvent(RegistrationFormEvent.Submit)
            },
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.errorContainer),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Text(text = stringResource(id = R.string.register_user_register_button))
        }
    }
}