package android.tvz.hr.clienttracker.data.remote

import android.tvz.hr.clienttracker.data.domain.model.User
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse
import android.tvz.hr.clienttracker.data.remote.model.RegisterResponse
import retrofit2.http.*


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
    @GET("/clients")
    suspend fun getClientsData(): List<ClientResponse>

    @Headers("Content-Type: application/json")
    @GET("/clients/{id}")
    suspend fun getClientsDetailsById(
        @Path("id") getById:String
    ): ClientResponse

}