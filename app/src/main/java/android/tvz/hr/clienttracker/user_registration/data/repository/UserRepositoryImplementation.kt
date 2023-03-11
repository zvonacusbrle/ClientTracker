package android.tvz.hr.clienttracker.user_registration.data.repository


import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.common.util.SessionManager
import android.tvz.hr.clienttracker.common.util.isNetworkConnected
import android.tvz.hr.clienttracker.data.domain.model.User
import android.tvz.hr.clienttracker.data.remote.ClientTrackerApi
import android.tvz.hr.clienttracker.user_registration.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(
    private val clientTrackerApi: ClientTrackerApi,
    private val sessionManager: SessionManager
) : UserRepository {
    override suspend fun registerUser(user: User): Result<String> {
        return try {
            if (!isNetworkConnected(sessionManager.context)) {
                Result.Error<String>(errorMessage = "No Internet")
            }

            val result = clientTrackerApi.registerAccount(user)
            if (result.success) {
                sessionManager.updateSession(result.message, user.username)
                Result.Success(errorMessage = "User created")
            } else {
                Result.Error(errorMessage = result.message)
            }
        } catch (e: java.lang.Exception) {
            Result.Error(errorMessage = e.message ?: "Problem")
        }
    }
}