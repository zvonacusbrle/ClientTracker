package android.tvz.hr.clienttracker.onboarding.screen

import android.graphics.Color.parseColor
import android.tvz.hr.clienttracker.onboarding.util.OnBoardingPage
import android.tvz.hr.clienttracker.ui.theme.backgroundColor
import android.view.LayoutInflater
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

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

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = 3,
            state = pagerState
        ) { position ->
            PagerScreen(onBoardingPage = screenPages[position])
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
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = onBoardingPage.contentDescription, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(60.dp))

    }
}

val String.color
    get() = Color(parseColor(this))