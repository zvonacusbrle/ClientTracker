package android.tvz.hr.clienttracker.clients_list.viewmodel

import android.tvz.hr.clienttracker.data.local.entities.ClientEntity

data class ClientState(
    val data: List<ClientEntity> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)