package android.tvz.hr.clienttracker.client_details.components

import android.tvz.hr.clienttracker.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetContent(clientName: String, clientWeight: List<Double>?, height: Double?) {
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
                text = clientName,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(30.dp))

            ClientHeightCard(height)
            Spacer(modifier = Modifier.height(10.dp))

            ClientWeightCard(clientWeight?.last())
            Spacer(modifier = Modifier.height(10.dp))

            clientWeight?.let {
                ClientWeightGraph(modifier = Modifier,
                    points = it,
                    paddingSpace = 12.dp,
                    verticalStep = 1 )
            }
        }
    }
}

@Composable
fun ClientHeightCard(height: Double?) {
    Row(){
        Image(painterResource(id = R.drawable.height_64), "weight_image")
        Text(text = "Client height $height")
    }
}

@Composable
fun ClientWeightCard(currentWeight: Double?) {
    Row() {
        val weightInKg = currentWeight?.times(10.0)
        Image(painterResource(id = R.drawable.weight_64), "weight_image")
        Text(text = "Current user weight $weightInKg kg")
    }
}

@Composable
@Preview
fun BottomSheetContentTest(){
    BottomSheetContent(
        clientName = "Miroslav",
        clientWeight = listOf(2.5, 3.3,4.4, 5.5,2.1,6.2 ),
        height = 181.2
    )
}