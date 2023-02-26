package android.tvz.hr.clienttracker.clients_list.viewmodel

import android.tvz.hr.clienttracker.clients_list.domain.repository.ClientsRepository
import android.tvz.hr.clienttracker.clients_list.domain.use_case.GetClientDataUseCase
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientListViewModel @Inject constructor(
    private val clientsRepository: ClientsRepository,
    private val getClientDataUseCase: GetClientDataUseCase
) : ViewModel() {
    private val _clientResponse: MutableState<ClientState> = mutableStateOf(ClientState())
    val clientResponse: State<ClientState> = _clientResponse

    init {
        viewModelScope.launch {
            val data = clientsRepository.getClientsData().data
            data?.let { getClientDataUseCase.invoke(it) }

            clientsRepository.getClients()
                .onStart {
                    _clientResponse.value = ClientState(
                        isLoading = true
                    )
                }.catch {
                    _clientResponse.value = ClientState(
                        error = it.message ?: "Something went wrong"
                    )
                }.collect {
                    _clientResponse.value = ClientState(
                        data = it
                    )
                }
        }
    }


}

