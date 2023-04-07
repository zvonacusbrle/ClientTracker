package android.tvz.hr.clienttracker.client_details.data

import android.tvz.hr.clienttracker.client_details.data.mapper.toClientDetails
import android.tvz.hr.clienttracker.client_details.domain.ClientDetailsRepository
import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.data.domain.model.ClientDetails
import android.tvz.hr.clienttracker.data.remote.ClientTrackerApi
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse
import javax.inject.Inject

class ClientDetailsRepositoryImp @Inject constructor(
    private val clientTrackerApi: ClientTrackerApi,
) : ClientDetailsRepository {
    override suspend fun getClientDetailsById(): Result<ClientDetails> {
        return try {
            val response = clientTrackerApi.getClientsDetailsById("1")
                .toClientDetails()
            Result.Success(data = response, errorMessage = "Client details")
        } catch (e: Exception) {
            Result.Error(data = null, errorMessage = e.message ?: "Problem")
        }
    }


}
