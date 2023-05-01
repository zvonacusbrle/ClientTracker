package android.tvz.hr.clienttracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(
    val route: String,
    val title: String = "",
    val icon: ImageVector? = null
    ) {
    object Welcome : Screen(route = "welcome_screen")
    object Home : Screen(route = "home_screen")
    object Splash : Screen(route = "splash_screen")
    object Register : Screen(route = "register_user")
    object Login : Screen(route = "login_screen")

    object ClientsListScreen : Screen(
        route = "clients_list_screen",
        title = "Client list",
        icon = Icons.Default.List
    )

    object ClientDetailsScreen : Screen(route = "client_details_screen/{$CLIENT_ID_KEY}"){
        fun passClientId(id: Int): String {
            return this.route.replace(oldValue = "{$CLIENT_ID_KEY}", newValue = id.toString())
        }
    }

    object CalendarScreen : Screen(
        route = "calendar_screen",
        title = "Calendar",
        icon = Icons.Filled.DateRange
    )



}
