package android.tvz.hr.clienttracker.client_details.components

import android.tvz.hr.clienttracker.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackgroundContent(
    picture: String?,
    imageFraction: Float = 1f
) {
    val painter =
        rememberImagePainter(
            data = picture
        ) {
            placeholder(R.drawable.default_client_profile_image)
            error(R.drawable.default_client_profile_image)
        }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(imageFraction)
                .align(Alignment.TopStart),
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = "Background image"
        )


    }
}