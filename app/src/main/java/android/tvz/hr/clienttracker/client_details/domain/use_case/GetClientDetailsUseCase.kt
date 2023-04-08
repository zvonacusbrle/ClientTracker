package android.tvz.hr.clienttracker.client_details.domain.use_case

import android.tvz.hr.clienttracker.client_details.domain.repository.ClientDetailsRepository
import android.tvz.hr.clienttracker.data.domain.model.ClientDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine
import android.tvz.hr.clienttracker.common.util.Result
import kotlin.coroutines.resume

class GetClientDetailsUseCase @Inject constructor(
    private val clientDetailsRepository: ClientDetailsRepository,
) {
    suspend operator fun invoke(clientId: Int) : Result<ClientDetails> = suspendCoroutine { continuation ->
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            val result = clientDetailsRepository.getClientDetailsById(clientId)
            continuation.resume(result)
        }
    }
}