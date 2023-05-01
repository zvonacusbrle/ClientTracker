package android.tvz.hr.clienttracker.client_details.data

import android.tvz.hr.clienttracker.client_details.data.mapper.toClientDetails
import android.tvz.hr.clienttracker.client_details.domain.repository.ClientDetailsRepository
import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.data.domain.model.ClientDetails
import android.tvz.hr.clienttracker.data.local.entities.ClientDao
import javax.inject.Inject

class ClientDetailsRepositoryImp @Inject constructor(
    private val clientDao: ClientDao
) : ClientDetailsRepository {

    override suspend fun getClientDetailsById(clientId: Int): Result<ClientDetails> {
        return try {
            val response = clientDao.getClientsDetailsById(clientId)
                .toClientDetails()

            Result.Success(data = response, errorMessage = "Client details")
        } catch (e: Exception) {
            Result.Error(data = null, errorMessage = e.message ?: "Problem")
        }
    }


}
