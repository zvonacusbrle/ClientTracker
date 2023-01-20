package android.tvz.hr.clienttracker

import android.os.Bundle
import android.tvz.hr.clienttracker.navigation.SetupNavGraph
import android.tvz.hr.clienttracker.onboarding.OnBoardingViewModel
import android.tvz.hr.clienttracker.ui.theme.ClientTrackerTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: OnBoardingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientTrackerTheme() {
                val startDestinationScreen = viewModel.startDestionation.value
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    startDestination = startDestinationScreen
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClientTrackerTheme {
        Greeting("Android")
    }
}