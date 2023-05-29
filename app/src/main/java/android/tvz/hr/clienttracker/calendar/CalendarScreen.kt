package android.tvz.hr.clienttracker.calendar

import AppBottomNavigation
import android.tvz.hr.clienttracker.ui.theme.blueBrush
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
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

    val calendarInputList by remember {
        mutableStateOf(createCalendarList())
    }

    var clickedCalendarElement by remember {
        mutableStateOf<CalendarInput?>(null)
    }



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
        ) {
            Calendar(
                calendarInput = calendarInputList,
                onDayClick = { day ->
                    clickedCalendarElement = calendarInputList.first { it.day == day }
                },
                month = monthString,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.3f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(Alignment.Center)
            ) {
                clickedCalendarElement?.toDo?.forEach{
                    Text(
                        if(it.contains("Day")) it else "- $it",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = if(it.contains("Day")) 25.sp else 18.sp
                    )
                }
            }
        }

    }


}

fun createCalendarList(): List<CalendarInput> {
    val calendarInputs = mutableListOf<CalendarInput>()
    for (i in 1..31) {
        calendarInputs.add(
            CalendarInput(
                i,
                toDo = listOf(
                    "Day $i",
                    "Nema ništa danas"
                )
            )
        )
    }
    return calendarInputs
}

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarInput: List<CalendarInput>,
    onDayClick: (Int) -> Unit,
    strokeWidth: Float = 15f,
    month: String
) {
    var canvasSize by remember {
        mutableStateOf(Size.Zero)
    }
    var clickAnimationOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var animationRadius by remember {
        mutableStateOf(0f)
    }
    var scope = rememberCoroutineScope()

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
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectTapGestures(
                        onTap = { offset ->
                            val column =
                                (offset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
                            val row = (offset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1
                            val day = column + (row - 1) * CALENDAR_COLUMNS
                            if (day <= calendarInput.size) {
                                onDayClick(day)
                                clickAnimationOffset = offset
                                scope.launch {
                                    animate(
                                        initialValue = 0f,
                                        targetValue = 255f,
                                        animationSpec = tween(durationMillis = 300)
                                    ) { value, _ ->
                                        animationRadius = value
                                    }
                                }
                            }

                        }
                    )

                }
        ) {
            val canvasHeight = size.height
            val canvasWidth = size.width
            canvasSize = Size(canvasWidth, canvasHeight)
            val ySteps = canvasHeight / CALENDAR_ROWS
            val xSteps = canvasWidth / CALENDAR_COLUMNS

            val column = (clickAnimationOffset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
            val row = (clickAnimationOffset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1

            val path = Path().apply {
                moveTo(x = (column - 1) * xSteps, y = (row - 1) * ySteps)
                lineTo(x = column * xSteps, y = (row - 1) * ySteps)
                lineTo(x = column * xSteps, y = row * ySteps)
                lineTo(x = (column - 1) * xSteps, row * ySteps)
                close()
            }

            clipPath(path) {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(Color.White.copy(0.8f), Color.White.copy(0.2f)),
                        center = clickAnimationOffset,
                        radius = animationRadius + 0.1f
                    ),
                    radius = animationRadius + 0.1f,
                    center = clickAnimationOffset
                )
            }

            drawRoundRect(
                brush = blueBrush,
                cornerRadius = CornerRadius(
                    x = 25f, y = 25f
                ),
                style = Stroke(
                    width = strokeWidth
                )
            )
            for (i in 1 until CALENDAR_ROWS) {
                drawLine(
                    color = Color.Blue,
                    start = Offset(x = 0f, y = ySteps * i),
                    end = Offset(x = canvasWidth, y = ySteps * i),
                    strokeWidth = strokeWidth
                )
            }
            for (i in 1 until CALENDAR_COLUMNS) {
                drawLine(
                    color = Color.Blue,
                    start = Offset(x = xSteps * i, y = 0f),
                    end = Offset(x = xSteps * i, y = canvasHeight),
                    strokeWidth = strokeWidth
                )
            }
            val textHeight = 17.dp.toPx()
            for (i in calendarInput.indices) {
                val textPositionX = xSteps * (i % CALENDAR_COLUMNS) + strokeWidth
                val textPositionY = (i / CALENDAR_COLUMNS) * ySteps + textHeight + strokeWidth / 2
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        "${i + 1}",
                        textPositionX,
                        textPositionY,
                        android.graphics.Paint().apply {
                            textSize = textHeight
                            color = Color.Blue.toArgb()
                            isFakeBoldText = true

                        }
                    )
                }
            }

        }
    }
}

data class CalendarInput(
    val day: Int,
    val toDo: List<String> = emptyList()
)

@Preview
@Composable
fun CalendarPreview() {
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






