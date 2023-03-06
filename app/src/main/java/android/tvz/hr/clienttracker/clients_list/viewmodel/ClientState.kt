package android.tvz.hr.clienttracker.clients_list.viewmodel

import android.tvz.hr.clienttracker.data.domain.model.Client

data class ClientState(
    val data: List<Client> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)