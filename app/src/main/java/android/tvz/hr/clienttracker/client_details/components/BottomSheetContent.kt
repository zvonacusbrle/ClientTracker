package android.tvz.hr.clienttracker.client_details.components

import android.tvz.hr.clienttracker.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetContent(clientName: String, clientWeight: List<Double>?) {
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
                text = clientName,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            clientWeight?.let {
                ClientWeightGraph(modifier = Modifier,
                    points = it,
                    paddingSpace = 12.dp,
                    verticalStep = 1 )
            }
        }
    }
}