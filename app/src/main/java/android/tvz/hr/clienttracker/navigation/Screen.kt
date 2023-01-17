package android.tvz.hr.clienttracker.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen(route = "welcome_screen")
    object Home : Screen(route = "home_screen")
    object Splash : Screen(route = "splash_screen")
    object Register : Screen(route = "register_user")
    object Login : Screen(route = "login_screen")
}
