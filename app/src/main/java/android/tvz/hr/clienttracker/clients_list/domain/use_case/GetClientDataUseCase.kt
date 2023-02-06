package android.tvz.hr.clienttracker.clients_list.domain.use_case

import android.tvz.hr.clienttracker.clients_list.domain.repository.ClientsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetClientDataUseCase @Inject constructor(
    private val clientsRepository: ClientsRepository
) {
    operator fun invoke(){
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            val clients = clientsRepository.getClientsData()
            clientsRepository.insertClients(clients)
        }

    }

}