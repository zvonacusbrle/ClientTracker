package android.tvz.hr.clienttracker.navigation


import android.tvz.hr.clienttracker.clients_list.screen.ClientsListScreen
import android.tvz.hr.clienttracker.login_user.screen.LoginUserScreen
import android.tvz.hr.clienttracker.onboarding.screen.HomeScreen
import android.tvz.hr.clienttracker.onboarding.screen.SplashScreen
import android.tvz.hr.clienttracker.onboarding.screen.WelcomeScreen
import android.tvz.hr.clienttracker.user_registration.screen.RegisterUserScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Splash.route) {
            SplashScreen()
        }
        composable(route = Screen.Register.route) {
            RegisterUserScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginUserScreen(navController = navController)
        }
        composable(route = Screen.ClientsListScreen.route){
            ClientsListScreen(navController = navController)
        }
    }
}