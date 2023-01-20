package android.tvz.hr.clienttracker.core.util.use_case

data class ValidationResults(
    val successful: Boolean,
    val errorMessage: String? = null
)