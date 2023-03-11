package android.tvz.hr.clienttracker.login_user.viewmodel

import android.app.Application
import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.common.util.use_case.ValidatePassword
import android.tvz.hr.clienttracker.common.util.use_case.ValidateUsername
import android.tvz.hr.clienttracker.data.domain.model.User
import android.tvz.hr.clienttracker.login_user.domain.LoginFormEvent
import android.tvz.hr.clienttracker.login_user.domain.LoginFormState
import android.tvz.hr.clienttracker.login_user.domain.repository.UserLoginRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateUsername: ValidateUsername,
    private val validatePassword: ValidatePassword,
    private val applicationContext: Application,
    private val userLoginRepository: UserLoginRepository
) : ViewModel() {

    var state by mutableStateOf(LoginFormState())

    private val _loginState = MutableStateFlow<Result<String>>(Result.Loading())
    val loginState = _loginState.asStateFlow()

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


        loginUser(state.username, state.password)

    }

    private fun loginUser(username: String, password: String) = viewModelScope.launch {
        val newUser = User(username, password)
        _loginState.emit(userLoginRepository.loginUser(newUser))
    }

}

