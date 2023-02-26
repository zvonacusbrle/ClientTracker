package android.tvz.hr.clienttracker.clients_list.domain.repository

import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse

interface ClientsRepository {
   suspend fun getClientsData() : Result<List<ClientResponse>>

   suspend fun insertClients(clients: List<ClientResponse>)
}