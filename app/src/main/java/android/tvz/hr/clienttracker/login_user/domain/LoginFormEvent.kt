package android.tvz.hr.clienttracker.login_user.domain

sealed class LoginFormEvent {
    data class UsernameChanged(val username: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()

    object Submit : LoginFormEvent()
}