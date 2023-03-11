package android.tvz.hr.clienttracker.common.util.use_case

import android.app.Application
import android.tvz.hr.clienttracker.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateUsername @Inject constructor() {

    fun execute(username: String, applicationContext: Application): ValidationResults {
        if (username.isBlank()) {
            return ValidationResults(
                successful = false,
                errorMessage = applicationContext.getString(R.string.register_user_username_field_to_short)

            )
        }
        if (username.length < 3) {
            return ValidationResults(
                successful = false,
                errorMessage = applicationContext.getString(R.string.register_user_username_field_to_short)

            )
        }
        return ValidationResults(
            successful = true,
        )
    }
}