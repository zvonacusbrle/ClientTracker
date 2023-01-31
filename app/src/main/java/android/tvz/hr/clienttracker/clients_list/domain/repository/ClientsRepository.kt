package android.tvz.hr.clienttracker.clients_list.domain.repository

import android.tvz.hr.clienttracker.common.util.Result
import android.tvz.hr.clienttracker.data.domain.model.Client
import android.tvz.hr.clienttracker.data.local.entities.ClientEntity
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse
import kotlinx.coroutines.flow.Flow

interface ClientsRepository {
    fun getAllClients() : List<Client>
}