package android.tvz.hr.clienttracker.client_details.domain.repository

import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.data.domain.model.ClientDetails


interface ClientDetailsRepository {
    suspend fun getClientDetailsById(clientId: Int) : Result<ClientDetails>
}