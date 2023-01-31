package android.tvz.hr.clienttracker.data.remote

import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.data.domain.model.User
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse
import android.tvz.hr.clienttracker.data.remote.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ClientTrackerApi {
    @Headers("Content-Type: application/json")
    @POST("/register")
    suspend fun registerAccount(
        @Body user: User
    ): RegisterResponse

    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun loginAccount(
        @Body user: User
    ): RegisterResponse

    @Headers("Content-Type: application/json")
    @POST("/clients")
    fun getAllClients(): List<ClientResponse>

}