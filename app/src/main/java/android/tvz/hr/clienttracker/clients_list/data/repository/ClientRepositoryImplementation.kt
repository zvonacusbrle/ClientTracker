package android.tvz.hr.clienttracker.clients_list.data.repository


import android.tvz.hr.clienttracker.clients_list.data.repository.mapper.toClient
import android.tvz.hr.clienttracker.clients_list.data.repository.mapper.toClientEntity
import android.tvz.hr.clienttracker.clients_list.domain.repository.ClientsRepository
import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.data.domain.model.Client
import android.tvz.hr.clienttracker.data.local.entities.ClientDao
import android.tvz.hr.clienttracker.data.remote.ClientTrackerApi
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClientRepositoryImplementation @Inject constructor(
    private val clientTrackerApi: ClientTrackerApi,
    private val clientDao: ClientDao
) : ClientsRepository {
    override suspend fun getClientsData(): Result<List<ClientResponse>> {
        return try {
            val response = clientTrackerApi.getClientsData()
            if (response.isNotEmpty()) {
                Result.Success(data = response, errorMessage = "Clients fetched")
            } else {
                Result.Error(data = emptyList(), errorMessage = "No clients found")
            }
        } catch (e: Exception) {
            Result.Error(data = emptyList(), errorMessage = e.message ?: "Problem")
        }
    }

    override suspend fun insertClients(clients: List<ClientResponse>) {
        clients.map { client ->
            clientDao.insertClient(
                client.toClientEntity()
            )
        }
    }

    override fun getClients(): Flow<List<Client>> {
        return clientDao.getClients().map {client ->
            client.map {clientEntity ->
                clientEntity.toClient()
            }
        }

    }

}