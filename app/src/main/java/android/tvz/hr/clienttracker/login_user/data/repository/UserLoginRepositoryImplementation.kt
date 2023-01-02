package android.tvz.hr.clienttracker.login_user.data.repository

import android.tvz.hr.clienttracker.core.local.User
import android.tvz.hr.clienttracker.core.remote.ClientTrackerApi
import android.tvz.hr.clienttracker.core.util.SessionManager
import android.tvz.hr.clienttracker.core.util.isNetworkConnected
import android.tvz.hr.clienttracker.login_user.domain.repository.UserLoginRepository
import android.tvz.hr.clienttracker.user_registration.Result
import javax.inject.Inject

class UserLoginRepositoryImplementation@Inject constructor(
    private val clientTrackerApi: ClientTrackerApi,
    private val sessionManager: SessionManager
    ) : UserLoginRepository {

    override suspend fun loginUser(user: User): Result<String> {
        return try {
            if (!isNetworkConnected(sessionManager.context)) {
                Result.Error<String>(errorMessage = "No Internet")
            }

            val result = clientTrackerApi.loginAccount(user)
            if (result.success) {
                sessionManager.updateSession(result.message, user.username)
                Result.Success(errorMessage = "Logged in successfully created")
            } else {
                Result.Error(errorMessage = result.message)
            }
        } catch (e: java.lang.Exception) {
            Result.Error(errorMessage = e.message ?: "Problem")
        }
    }

}