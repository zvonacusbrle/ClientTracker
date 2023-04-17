package android.tvz.hr.clienttracker.client_details.components

import android.tvz.hr.clienttracker.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackgroundContent(
    picture: String?,
    imageFraction: Float = 1f,
    onBackClicked: () -> Unit
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
                .fillMaxHeight(imageFraction + 0.4f)
                .align(Alignment.TopStart),
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = "Background image"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                modifier = Modifier.padding(all = 15.dp),
                onClick = { onBackClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back button",
                    tint = Color.White
                )
            }
        }


    }
}