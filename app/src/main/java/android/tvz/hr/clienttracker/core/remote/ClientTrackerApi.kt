package android.tvz.hr.clienttracker.core.remote

import android.tvz.hr.clienttracker.core.local.User
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ClientTrackerApi {
    @Headers("Content-Type: application/json")
    @POST("/register")
    suspend fun registerAccount(
        @Body user: User
    ) : RegisterResponse

    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun loginAccount(
        @Body user: User
    ) : RegisterResponse
}