package android.tvz.hr.clienttracker.common.util.use_case

data class ValidationResults(
    val successful: Boolean,
    val errorMessage: String? = null
)