package android.tvz.hr.clienttracker.user_registration.domain

data class RegistrationFormState(
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null
) {
}