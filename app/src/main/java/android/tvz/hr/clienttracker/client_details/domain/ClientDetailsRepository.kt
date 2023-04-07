package android.tvz.hr.clienttracker.client_details.domain

import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.data.domain.model.ClientDetails
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse

interface ClientDetailsRepository {
    suspend fun getClientDetailsById() : Result<ClientDetails>
}