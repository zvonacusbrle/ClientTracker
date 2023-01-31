package android.tvz.hr.clienttracker.user_registration.domain.repository

import android.tvz.hr.clienttracker.data.domain.model.User
import android.tvz.hr.clienttracker.common.util.Result

interface UserRepository {
    suspend fun registerUser(user: User): Result<String>
}