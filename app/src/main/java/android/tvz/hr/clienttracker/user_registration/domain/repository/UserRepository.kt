package android.tvz.hr.clienttracker.user_registration.domain.repository

import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.data.domain.model.User

interface UserRepository {
    suspend fun registerUser(user: User): Result<String>
}