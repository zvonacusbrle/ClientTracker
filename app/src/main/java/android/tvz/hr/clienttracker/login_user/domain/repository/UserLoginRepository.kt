package android.tvz.hr.clienttracker.login_user.domain.repository

import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.data.domain.model.User

interface UserLoginRepository {
    suspend fun loginUser(user: User): Result<String>
    suspend fun getUser(): Result<User>
    suspend fun logout(): Result<String>
}