package android.tvz.hr.clienttracker

import android.os.Bundle
import android.tvz.hr.clienttracker.navigation.Screen
import android.tvz.hr.clienttracker.navigation.SetupNavGraph
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.tvz.hr.clienttracker.ui.theme.ClientTrackerTheme
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientTrackerTheme() {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController, startDestination = Screen.Welcome.route)
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