package android.tvz.hr.clienttracker.login_user.domain.repository

import android.tvz.hr.clienttracker.core.local.User
import android.tvz.hr.clienttracker.user_registration.Result

interface UserLoginRepository {
    suspend fun loginUser(user: User) : Result<String>
}