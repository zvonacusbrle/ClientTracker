package android.tvz.hr.clienttracker.user_registration.data.repository


import android.tvz.hr.clienttracker.core.local.User
import android.tvz.hr.clienttracker.core.remote.ClientTrackerApi
import android.tvz.hr.clienttracker.core.util.SessionManager
import android.tvz.hr.clienttracker.core.util.isNetworkConnected
import android.tvz.hr.clienttracker.user_registration.Result
import android.tvz.hr.clienttracker.user_registration.domain.repository.ClientRepository
import javax.inject.Inject

class ClientRepositoryImplementation @Inject constructor(
    private val clientTrackerApi: ClientTrackerApi,
    private val sessionManager: SessionManager
) : ClientRepository {
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