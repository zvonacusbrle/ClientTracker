package android.tvz.hr.clienttracker.login_user.domain

data class LoginFormState(
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
) {

}