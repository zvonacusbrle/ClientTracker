package android.tvz.hr.clienttracker.user_registration.domain.repository

import android.tvz.hr.clienttracker.core.local.User
import android.tvz.hr.clienttracker.user_registration.Result

interface ClientRepository {
    suspend fun registerUser(user: User) : Result<String>
}