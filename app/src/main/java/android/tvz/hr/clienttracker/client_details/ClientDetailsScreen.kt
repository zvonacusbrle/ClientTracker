package android.tvz.hr.clienttracker.client_details

import android.tvz.hr.clienttracker.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ClientDetailsScreen(
    navController: NavController,
    clientId: Int?
) {
    Text(text = "Client ID: $clientId ")
    DetailsContent()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsContent() {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 150.dp,
        sheetContent = {
            BottomSheetContent()
        },
        content = {}

    )


}

@Composable
fun BottomSheetContent() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.bottom_sheet)


    ) {
        Column(
            modifier = Modifier
                .padding(all = 20.dp)
                .fillMaxWidth()



            ) {
            Text(
                modifier = Modifier.weight(8f),
                text = "Ronnie Coleman",
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }

}

@Preview
@Composable
fun DetailsContentPreview() {
    DetailsContent()
}