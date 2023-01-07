package android.tvz.hr.clienttracker.login_user.viewmodel

import android.app.Application
import android.content.ContentValues
import android.tvz.hr.clienttracker.core.util.use_case.ValidatePassword
import android.tvz.hr.clienttracker.core.util.use_case.ValidateUsername
import android.tvz.hr.clienttracker.login_user.domain.LoginFormEvent
import android.tvz.hr.clienttracker.login_user.domain.LoginFormState
import android.tvz.hr.clienttracker.login_user.domain.repository.UserLoginRepository
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateUsername: ValidateUsername,
    private val validatePassword: ValidatePassword,
    private val applicationContext: Application,
    private val userLoginRepository: UserLoginRepository
) : ViewModel() {

    var state by mutableStateOf(LoginFormState())

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.UsernameChanged -> {
                state = state.copy(username = event.username)
            }
            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val usernameResult = validateUsername.execute(state.username, applicationContext)
        val passwordResult = validatePassword.execute(state.password, applicationContext)

        val hasErrors = listOf(
            usernameResult,
            passwordResult,
        ).any { !it.successful }

        state = state.copy(
            usernameError = usernameResult.errorMessage,
            passwordError = passwordResult.errorMessage,
        )
        if (hasErrors) {
            return
        }


    }

}

