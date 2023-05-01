import android.tvz.hr.clienttracker.navigation.Screen
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun AppBottomNavigation(navController: NavHostController) {
    val items = listOf(
        Screen.ClientsListScreen,
        Screen.CalendarScreen
    )
    BottomNavigation(
        backgroundColor = Color.Cyan
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    when (screen) {
                        Screen.ClientsListScreen -> Icon(Icons.Filled.List, contentDescription = "Clients")
                        Screen.CalendarScreen -> Icon(Icons.Filled.DateRange, contentDescription = "Calendar")
                        else -> error("Error for route ${screen.route}")
                    }
                },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationPreview(){
    AppBottomNavigation(navController = rememberNavController())
}

