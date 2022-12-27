package android.tvz.hr.clienttracker.onboarding.screen

import android.graphics.Color.parseColor
import android.tvz.hr.clienttracker.navigation.Screen
import android.tvz.hr.clienttracker.onboarding.util.OnBoardingPage
import android.tvz.hr.clienttracker.onboarding.util.OnboardingPrefs
import android.tvz.hr.clienttracker.ui.theme.backgroundColor
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    navController: NavController
) {
    val screenPages = listOf(
        OnBoardingPage.FirstScreen,
        OnBoardingPage.SecondScreen,
        OnBoardingPage.ThirdScreen
    )

    val pagerState = rememberPagerState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = OnboardingPrefs(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPage = screenPages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState
        )
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            scope.launch {
                dataStore.saveOnboarding(completed = true)
            }
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(5.dp)

    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier
                    .height(68.dp)
                    .width(100.dp)
                    .align(Alignment.Center)
                    .background(MaterialTheme.colors.backgroundColor),
            ) {
                Text(text = "Finish", color = Color.White)
            }
        }

    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .padding(60.dp)
                .fillMaxSize()
                .aspectRatio(1f)
                .background(
                    color = MaterialTheme.colors.backgroundColor, shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = onBoardingPage.image),
                contentDescription = "fragment picture",
                modifier = Modifier
                    .height(225.dp)
                    .width(225.dp)
            )
        }
        Text(text = onBoardingPage.title, fontSize = 28.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = onBoardingPage.contentDescription, fontSize = 16.sp)

    }
}

val String.color
    get() = Color(parseColor(this))

@Preview
@Composable
fun WelcomeScreenPreview() {
    PagerScreen(onBoardingPage = OnBoardingPage.FirstScreen)

}