package android.tvz.hr.clienttracker.clients_list.components

import android.annotation.SuppressLint
import android.tvz.hr.clienttracker.R
import android.tvz.hr.clienttracker.clients_list.screen.CLIENT_ITEM_HEIGHT
import android.tvz.hr.clienttracker.clients_list.screen.MEDIUM_PADDING
import android.tvz.hr.clienttracker.data.domain.model.Client
import android.tvz.hr.clienttracker.navigation.Screen
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ClientCardItem(client: Client, navController: NavController) {
    val painter =
        rememberImagePainter(
            data = client.picture
        ) {
            placeholder(R.drawable.default_client_profile_image)
            error(R.drawable.default_client_profile_image)
        }

    Box(
        modifier = Modifier
            .height(CLIENT_ITEM_HEIGHT)
            .padding(top = MEDIUM_PADDING)
            .clip(
                RoundedCornerShape(
                    topStart = MEDIUM_PADDING,
                    topEnd = MEDIUM_PADDING,
                    bottomStart = MEDIUM_PADDING,
                    bottomEnd = MEDIUM_PADDING
                )
            )


            .clickable {
                navController.navigate(route = Screen.ClientDetailsScreen.passClientId(client.id))
            },
        contentAlignment = Alignment.BottomStart,
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
                .fillMaxHeight(0.18f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = MEDIUM_PADDING,
                bottomEnd = MEDIUM_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 15.dp)
            ) {
                Text(
                    text = client.name,
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