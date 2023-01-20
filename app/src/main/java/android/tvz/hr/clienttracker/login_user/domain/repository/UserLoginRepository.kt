package android.tvz.hr.clienttracker.login_user.domain.repository

import android.tvz.hr.clienttracker.core.local.User
import android.tvz.hr.clienttracker.core.util.Result

interface UserLoginRepository {
    suspend fun loginUser(user: User): Result<String>
    suspend fun getUser(): Result<User>
    suspend fun logout(): Result<String>
}