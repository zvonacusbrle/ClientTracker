package android.tvz.hr.clienttracker.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen(route = "welcome_screen")
    object Home : Screen(route = "home_screen")
    object Splash : Screen(route = "splash_screen")
    object Register : Screen(route = "register_user")
    object Login : Screen(route = "login_screen")

    object ClientsListScreen : Screen(route = "clients_list_screen")

    object ClientDetailsScreen : Screen(route = "client_details_screen/{$CLIENT_ID_KEY}"){
        fun passClientId(id: Int): String {
            return this.route.replace(oldValue = "{$CLIENT_ID_KEY}", newValue = id.toString())
        }
    }



}
