package android.tvz.hr.clienttracker.user_registration.viewmodel.use_case

data class ValidationResults(
    val successful: Boolean,
    val errorMessage: String? = null
)