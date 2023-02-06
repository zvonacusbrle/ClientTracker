package android.tvz.hr.clienttracker.clients_list.data.repository


import android.content.ContentValues.TAG
import android.tvz.hr.clienttracker.clients_list.data.repository.mapper.toClientEntity
import android.tvz.hr.clienttracker.clients_list.domain.repository.ClientsRepository
import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.data.domain.model.Client
import android.tvz.hr.clienttracker.data.local.entities.ClientDao
import android.tvz.hr.clienttracker.data.remote.ClientTrackerApi
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse
import android.util.Log
import javax.inject.Inject

class ClientRepositoryImplementation @Inject constructor(
    private val clientTrackerApi: ClientTrackerApi,
    private val clientDao: ClientDao
) : ClientsRepository {
    override suspend fun getClientsData(): List<ClientResponse> {
        return clientTrackerApi.getClientsData()
    }

        override suspend fun insertClients(clients: List<ClientResponse>) {
            clients.map { clientResponse ->
                clientDao.insertClient(
                    clientResponse.toClientEntity()
                )
            }
        }
    }