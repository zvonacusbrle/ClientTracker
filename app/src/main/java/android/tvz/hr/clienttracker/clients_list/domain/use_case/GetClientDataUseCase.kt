package android.tvz.hr.clienttracker.clients_list.domain.use_case

import android.tvz.hr.clienttracker.clients_list.domain.repository.ClientsRepository
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetClientDataUseCase @Inject constructor(
    private val clientsRepository: ClientsRepository,
) {
    operator fun invoke(clients: List<ClientResponse>) {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            clientsRepository.insertClients(clients)
        }

    }

}