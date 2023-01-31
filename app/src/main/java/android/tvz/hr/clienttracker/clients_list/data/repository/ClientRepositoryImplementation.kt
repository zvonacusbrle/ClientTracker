package android.tvz.hr.clienttracker.clients_list.data.repository

import android.tvz.hr.clienttracker.clients_list.data.repository.mapper.toClient
import android.tvz.hr.clienttracker.clients_list.domain.repository.ClientsRepository
import android.tvz.hr.clienttracker.data.domain.model.Client
import android.tvz.hr.clienttracker.data.remote.ClientTrackerApi


import javax.inject.Inject

class ClientRepositoryImplementation @Inject constructor(
    private val clientTrackerApi: ClientTrackerApi,
) : ClientsRepository {

    override fun getAllClients(): List<Client> {
        return  clientTrackerApi.getAllClients().map { clientResponse ->
            clientResponse.toClient()
        }
    }
}