package android.tvz.hr.clienttracker.client_details.screen

import android.content.ContentValues
import android.tvz.hr.clienttracker.client_details.components.BackgroundContent
import android.tvz.hr.clienttracker.client_details.components.BottomSheetContent
import android.tvz.hr.clienttracker.client_details.viewmodel.ClientDetailsViewModel
import android.tvz.hr.clienttracker.data.domain.model.ClientDetails
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import timber.log.Timber

@Composable
fun ClientDetailsScreen(
    navController: NavController,
    clientId: Int?
) {
    val viewModel = hiltViewModel<ClientDetailsViewModel>()
    clientId?.let { viewModel.getClientDetailsById(clientId = it) }
    val clientDetailsResponse = viewModel.clientDetailsResponse.value

    Text(text = "Client ID: ${clientDetailsResponse.data} ")

    if (clientDetailsResponse.data != null) {
        DetailsContent(clientDetailsResponse.data)
    }

    if (clientDetailsResponse.error.isNotEmpty())
        Timber.tag(ContentValues.TAG).d("ClientsListScreen: ERROR")

    if (clientDetailsResponse.isLoading) {

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsContent(client: ClientDetails) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 150.dp,
        sheetContent = {
            BottomSheetContent(client.name, client.weight)
        },
        content = {

            BackgroundContent(
                picture = client.picture,
                imageFraction = currentSheetFraction,
            )
        }

    )


}

@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }


@Preview
@Composable
fun DetailsContentPreview() {
    DetailsContent(
        ClientDetails(
            id = 1,
            age = 2,
            name = "3"
        ),
    )
}

