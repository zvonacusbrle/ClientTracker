package android.tvz.hr.clienttracker.client_details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ClientDetailsScreen(
    navController: NavController,
    clientId: Int?
) {
    Text(text = "Client ID: $clientId ")
}