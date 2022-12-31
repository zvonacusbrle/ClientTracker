package android.tvz.hr.clienttracker.user_registration.screen

import android.tvz.hr.clienttracker.R
import android.tvz.hr.clienttracker.user_registration.viewmodel.RegistrationViewModel
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import android.tvz.hr.clienttracker.user_registration.Result
import android.tvz.hr.clienttracker.user_registration.domain.RegistrationFormEvent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun RegisterUserScreen() {
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
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
            modifier = Modifier
                .align(Alignment.Start)
                .width(40.dp)
                .height(30.dp),
              //  .clickable { findNavController(registerUserFragment).navigate(R.id.action_registerUser_to_logIn) },
            contentDescription = stringResource(id = R.string.register_user_button_back)
        )
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
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.primary
            ),
            isError = state.usernameError != null,
            leadingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "Person icon")},
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
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.primary
            ),
            isError = state.passwordError != null,
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Password icon")},

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
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.primary
            ),
            isError = state.repeatedPasswordError != null,
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Password icon")},

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
            border = BorderStroke(1.dp, color = MaterialTheme.colors.primaryVariant),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colors.primaryVariant
            )
        ) {
            Text(text = stringResource(id = R.string.register_user_register_button))
        }
    }
}