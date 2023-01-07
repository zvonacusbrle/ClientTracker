package android.tvz.hr.clienttracker.user_registration.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.tvz.hr.clienttracker.core.local.User
import android.tvz.hr.clienttracker.core.util.Result
import android.tvz.hr.clienttracker.core.util.use_case.ValidatePassword
import android.tvz.hr.clienttracker.core.util.use_case.ValidateRepeatedPassword
import android.tvz.hr.clienttracker.core.util.use_case.ValidateUsername
import android.tvz.hr.clienttracker.user_registration.domain.RegistrationFormEvent
import android.tvz.hr.clienttracker.user_registration.domain.RegistrationFormState
import android.tvz.hr.clienttracker.user_registration.domain.repository.ClientRepository
import android.util.Log
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
class RegistrationViewModel @Inject constructor(
    private val validateUsername: ValidateUsername,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword,
    private val applicationContext: Application,
    private val clientRepository: ClientRepository,
) : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    private val _registerState = MutableStateFlow<Result<String>>(Result.Loading())
    val registerState = _registerState.asStateFlow()

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.UsernameChanged -> {
                state = state.copy(username = event.username)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val usernameResult = validateUsername.execute(state.username, applicationContext)
        val passwordResult = validatePassword.execute(state.password,applicationContext)
        val repeatedPasswordResult = validateRepeatedPassword
            .execute(state.password, state.repeatedPassword, applicationContext)

        Log.d(TAG, "submitData: $usernameResult $passwordResult $repeatedPasswordResult")

        val hasErrors = listOf(
            usernameResult,
            passwordResult,
            repeatedPasswordResult
        ).any { !it.successful }

        state = state.copy(
            usernameError = usernameResult.errorMessage,
            passwordError = passwordResult.errorMessage,
            repeatedPasswordError = repeatedPasswordResult.errorMessage
        )
        if (hasErrors) {
            return
        }

        createUser(state.username,state.password)

    }

    private fun createUser(username: String, password: String) = viewModelScope.launch {
        _registerState.emit(Result.Loading())
        val newUser = User(username,password)
        _registerState.emit(clientRepository.registerUser(newUser))

    }
}

