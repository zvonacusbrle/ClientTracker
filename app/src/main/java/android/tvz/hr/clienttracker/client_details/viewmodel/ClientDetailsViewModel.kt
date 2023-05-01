package android.tvz.hr.clienttracker.client_details.viewmodel

import android.tvz.hr.clienttracker.client_details.domain.repository.ClientDetailsRepository
import android.tvz.hr.clienttracker.client_details.domain.use_case.GetClientDetailsUseCase
import android.tvz.hr.clienttracker.common.util.Result
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientDetailsViewModel @Inject constructor(
    private val clientDetailsRepository: ClientDetailsRepository,
    private val getClientDetailsUseCase: GetClientDetailsUseCase
) : ViewModel() {
    private val _clientDetailsResponse: MutableState<ClientDetailsState> =
        mutableStateOf(ClientDetailsState(isLoading = true))
    val clientDetailsResponse: State<ClientDetailsState> = _clientDetailsResponse

    fun getClientDetailsById(clientId: Int) {
        viewModelScope.launch {
            val result = getClientDetailsUseCase.invoke(clientId)
            when (result) {
                is Result.Success -> {
                    _clientDetailsResponse.value = ClientDetailsState(
                        data = result.data
                    )
                }
                is Result.Error -> {
                    _clientDetailsResponse.value = ClientDetailsState(
                        error = result.errorMessage ?: "Something went wrong"
                    )
                }
                is Result.Loading -> {
                    _clientDetailsResponse.value = ClientDetailsState(
                        isLoading = true
                    )
                }


            }
        }
    }

}