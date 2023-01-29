package android.tvz.hr.clienttracker.clients_list.screen


import android.annotation.SuppressLint
import android.net.Uri
import android.tvz.hr.clienttracker.R
import android.tvz.hr.clienttracker.ui.theme.Shapes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter


@SuppressLint("UnrememberedMutableState")
@Composable
fun ClientsListScreen(
    navController: NavController
) {
    ClientCardItem()
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ClientCardItem() {
    val uri = "content://com.android.providers.media.documents/document/image%3A18".toUri()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current




    val painter =
        rememberImagePainter(
            data = "https://gymbeam.cz/blog/wp-content/uploads/2016/09/RONNIE_COLEMAN.jpg"
        ) {
            placeholder(R.drawable.default_client_profile_image)
            error(R.drawable.default_client_profile_image)
        }

    Box(modifier = Modifier
        .height(CLIENT_ITEM_HEIGHT)
        .clickable {},
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
                    text = "Ronnie Coleman",
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