package android.tvz.hr.clienttracker.clients_list.data.repository


import android.tvz.hr.clienttracker.clients_list.data.repository.mapper.toClient
import android.tvz.hr.clienttracker.clients_list.data.repository.mapper.toClientEntity
import android.tvz.hr.clienttracker.clients_list.domain.repository.ClientsRepository
import android.tvz.hr.clienttracker.data.domain.model.Client
import android.tvz.hr.clienttracker.data.local.entities.ClientDao
import android.tvz.hr.clienttracker.data.remote.ClientTrackerApi
import javax.inject.Inject

class ClientRepositoryImplementation @Inject constructor(
    private val clientTrackerApi: ClientTrackerApi,
    private val clientDao: ClientDao
) : ClientsRepository {

    override fun getAllClients(): List<Client> {
        return clientTrackerApi.getAllClients().map { clientResponse ->
            clientResponse.toClient()
        }
    }

    override suspend fun insertClients(clients: List<Client>) {
        clients.map { client ->
            clientDao.insertClient(
                client.toClientEntity()
            )
        }
    }
}