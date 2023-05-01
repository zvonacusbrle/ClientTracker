package android.tvz.hr.clienttracker.client_details.viewmodel

import android.tvz.hr.clienttracker.data.domain.model.ClientDetails

data class ClientDetailsState(
    val data: ClientDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false
)