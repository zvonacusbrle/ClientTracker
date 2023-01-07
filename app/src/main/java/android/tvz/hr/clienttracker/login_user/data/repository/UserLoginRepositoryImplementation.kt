package android.tvz.hr.clienttracker.login_user.data.repository

import android.tvz.hr.clienttracker.core.local.User
import android.tvz.hr.clienttracker.core.remote.ClientTrackerApi
import android.tvz.hr.clienttracker.core.util.Result
import android.tvz.hr.clienttracker.core.util.SessionManager
import android.tvz.hr.clienttracker.core.util.isNetworkConnected
import android.tvz.hr.clienttracker.login_user.domain.repository.UserLoginRepository
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

    override suspend fun getUser(): Result<User> {
        return try {
            val name = sessionManager.getCurrentUsername()
            val password = sessionManager.getCurrentPassword()
            if(name == null || password == null){
               Result.Error<User>(errorMessage = "There is problem with user")
            }
            Result.Success(data = User(username = name!!))

        }
        catch (e: java.lang.Exception){
            e.printStackTrace()
            Result.Error(errorMessage = e.message ?: "Problem")
        }
    }

    override suspend fun logout(): Result<String> {
        return try {
            sessionManager.logout()
            Result.Success(errorMessage = "Logged out successfully")
        } catch (e: java.lang.Exception){
            e.printStackTrace()
            Result.Error(errorMessage = e.message ?: "Problem with log out")
        }
    }


}