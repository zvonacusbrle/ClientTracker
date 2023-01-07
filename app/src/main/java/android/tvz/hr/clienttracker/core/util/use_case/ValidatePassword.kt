package android.tvz.hr.clienttracker.core.util.use_case

import android.app.Application
import android.tvz.hr.clienttracker.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidatePassword @Inject constructor() {

    fun execute(password: String, applicationContext: Application): ValidationResults {
        if (password.isBlank()) {
            return ValidationResults(
                successful = false,
                errorMessage = applicationContext.getString(R.string.register_user_password_lenght)

            )
        }
        if (password.length < 6 || password.length > 8)
            return ValidationResults(
                successful = true,
                errorMessage = applicationContext.getString(R.string.register_user_password_lenght)

            )
        return ValidationResults(
            successful = true
        )
    }
}