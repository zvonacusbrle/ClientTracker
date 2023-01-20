package android.tvz.hr.clienttracker.core.util

sealed class Result<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T? = null, errorMessage: String? = null) : Result<T>(data, errorMessage)
    class Error<T>(data: T? = null, errorMessage: String) : Result<T>(data, errorMessage)
    class Loading<T> : Result<T>()
}