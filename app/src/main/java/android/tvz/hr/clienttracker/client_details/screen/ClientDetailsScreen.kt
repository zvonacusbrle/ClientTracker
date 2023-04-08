package android.tvz.hr.clienttracker.client_details

import android.content.ContentValues
import android.tvz.hr.clienttracker.R
import android.tvz.hr.clienttracker.client_details.components.BackgroundImage
import android.tvz.hr.clienttracker.client_details.components.BottomSheetContent
import android.tvz.hr.clienttracker.client_details.components.ClientWeightGraph
import android.tvz.hr.clienttracker.client_details.viewmodel.ClientDetailsViewModel
import android.tvz.hr.clienttracker.data.domain.model.ClientDetails
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
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

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 150.dp,
        sheetContent = {
            BottomSheetContent(client.name, client.weight)
        },
        content = {
            BackgroundImage(client.picture)
        }

    )


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

