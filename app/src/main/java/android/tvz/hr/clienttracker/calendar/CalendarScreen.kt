package android.tvz.hr.clienttracker.calendar

import AppBottomNavigation
import android.tvz.hr.clienttracker.ui.theme.dark_light_redContainer
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import timber.log.Timber
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController: NavHostController) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val currentMonth = LocalDate.now().month
    val monthString = currentMonth.getDisplayName(TextStyle.FULL, Locale.getDefault())

    Scaffold(
        bottomBar = {
            AppBottomNavigation(navController = navController)
        },
        containerColor = Color.White
    ) { padding ->
        Timber.i("CalendarScreen padding: $padding")
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red),
            contentAlignment = Alignment.TopCenter
        ){
            Calendar(
                calendarInput = emptyList(),
                onDayClick = {},
                month = monthString,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.3f)
            )
        }

    }


}

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarInput: List<CalendarInput>,
    onDayClick: (Int) -> Unit,
    strokeWidth: Float = 15f,
    month: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = month,
            fontWeight = FontWeight.SemiBold,
            color = Color.LightGray,
            fontSize = 40.sp
        )
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val canvasHeight = size.height
            val canvasWidth = size.width
            val ySteps = canvasHeight / CALENDAR_ROWS
            val xSteps = canvasWidth / CALENDAR_COLUMNS

            drawRoundRect(
                brush =   Brush.radialGradient(
                    listOf(Color(0xFF2be4dc), Color(0xFF243484))
                ),
                cornerRadius = CornerRadius(
                    x = 25f, y = 25f
                ),
                style = Stroke(
                    width = strokeWidth
                )
            )

        }
    }
}

data class CalendarInput(
    val day: Int,
    val toDo: List<String> = emptyList()
)

@Preview
@Composable
fun CalendarPreview(){
    val currentMonth = LocalDate.now().month
    val monthString = currentMonth.getDisplayName(TextStyle.FULL, Locale.getDefault())
    Calendar(
        calendarInput = emptyList(),
        onDayClick = {},
        month = monthString
    )
}

private const val CALENDAR_ROWS = 5
private const val CALENDAR_COLUMNS = 7






