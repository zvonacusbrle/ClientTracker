package android.tvz.hr.clienttracker.clients_list.screen

import android.tvz.hr.clienttracker.R
import android.tvz.hr.clienttracker.ui.theme.Shapes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@Composable
fun ClientsListScreen(
    navController: NavController
) {
    ClientCardItem()
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ClientCardItem() {
    val painter =
        rememberImagePainter(
            data = "https://www.pngitem.com/pimgs/m/150-1503945_transparent-user-png-default-user-image-png-png.png"
        ) {
            placeholder(R.drawable.default_client_profile_image)
            error(R.drawable.default_client_profile_image)
        }
    Box(modifier = Modifier
        .height(CLIENT_ITEM_HEIGHT)
        .clickable { /*NAVCONTOLLER*/ },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = Shapes.large) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.client_screen_image_description),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.2f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 15.dp)
            ) {
                Text(
                    text = "Zvonimir",
                    color = Color.White,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
fun ClientCardPreview() {
    ClientCardItem()
}

val CLIENT_ITEM_HEIGHT = 350.dp
val LARGE_PADDING = 20.dp