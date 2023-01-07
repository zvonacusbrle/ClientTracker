package android.tvz.hr.clienttracker.onboarding.util

import android.tvz.hr.clienttracker.R
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color


sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val contentDescription: String,

) {
    object FirstScreen : OnBoardingPage(
        image = R.drawable.first_screen_onboarding_picture_10,
        title = "Check users quickly" ,
        contentDescription = "This app will allow you to check your clients in seconds",

    )
    object SecondScreen : OnBoardingPage(
        image =R.drawable.second_screen_onboarding_picture_10,
        title =  "",
        contentDescription = "Insert or delete clients to update your client list",

    )
    object ThirdScreen : OnBoardingPage(
        image = R.drawable.third_screen_onboarding_picture_10,
        title = "" ,
        contentDescription = "Thank you for installing this application",

    )
}

val String.color
    get() = Color(android.graphics.Color.parseColor(this))