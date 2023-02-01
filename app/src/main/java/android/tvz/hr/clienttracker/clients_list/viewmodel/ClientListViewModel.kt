package android.tvz.hr.clienttracker.clients_list.viewmodel

import android.tvz.hr.clienttracker.clients_list.domain.repository.ClientsRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientListViewModel @Inject constructor(
    private val clientsRepository: ClientsRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            val clients = clientsRepository.getAllClients()
            clientsRepository.insertClients(clients)
        }
    }
}