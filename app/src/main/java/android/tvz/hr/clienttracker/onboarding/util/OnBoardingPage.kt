package android.tvz.hr.clienttracker.onboarding.util

import android.tvz.hr.clienttracker.R
import androidx.annotation.DrawableRes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val contentDescription: String
) {

    object FirstScreen : OnBoardingPage(
        image =R.drawable.first_screen_onboarding_picture,
        title = "Check users quickly" ,
        contentDescription = "This app will allow you to check your clients in seconds"
    )
    object SecondScreen : OnBoardingPage(
        image =R.drawable.second_screen_onboarding_picture,
        title =  "",
        contentDescription = "Insert or delete clients to update your client list"
    )
    object ThirdScreen : OnBoardingPage(
        image = R.drawable.third_screen_onboarding_picture,
        title = "" ,
        contentDescription = "Thank you for installing this application"
    )
}