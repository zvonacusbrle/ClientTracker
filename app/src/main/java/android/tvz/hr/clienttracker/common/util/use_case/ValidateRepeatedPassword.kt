package android.tvz.hr.clienttracker.common.util.use_case

import android.app.Application
import android.tvz.hr.clienttracker.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateRepeatedPassword @Inject constructor() {

    fun execute(
        password: String,
        repeatedPassword: String,
        applicationContext: Application
    ): ValidationResults {
        if (repeatedPassword.isBlank()) {
            return ValidationResults(
                successful = false,
                errorMessage = applicationContext.getString(R.string.register_user_blank_password)
            )
        }
        if (password != repeatedPassword) {
            return ValidationResults(
                successful = false,
                errorMessage = applicationContext.getString(R.string.register_user_different_passwords)
            )
        }
        return ValidationResults(
            successful = true
        )
    }
}