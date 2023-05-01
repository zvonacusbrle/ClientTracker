package android.tvz.hr.clienttracker.calendar

import AppBottomNavigation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController: NavHostController){
        Scaffold(
                bottomBar = {
                        AppBottomNavigation(navController = navController)
                }
        ){ padding ->
                Text(text = "$padding")
        }
}