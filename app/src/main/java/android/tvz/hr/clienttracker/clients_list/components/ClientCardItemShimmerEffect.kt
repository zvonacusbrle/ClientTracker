package android.tvz.hr.clienttracker.clients_list.components

import android.tvz.hr.clienttracker.clients_list.screen.CLIENT_ITEM_HEIGHT
import android.tvz.hr.clienttracker.ui.theme.md_theme_dark_onBackground
import android.tvz.hr.clienttracker.ui.theme.md_theme_light_outlineVariant
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ClientCardItemShimmerEffect() {

}

@Composable
fun AnimatedShimmerItem(){
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(alpha: Float){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(CLIENT_ITEM_HEIGHT),
    color = md_theme_dark_onBackground,
    shape = RoundedCornerShape(size = 20.dp)
    ) {
        Column(modifier = Modifier
            .alpha(alpha = alpha)
            .padding(all = 15.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(modifier = Modifier
                .alpha(alpha = alpha)
                .fillMaxWidth(0.5f)
                .height(30.dp),
                color = md_theme_light_outlineVariant,
                shape = RoundedCornerShape(size = 15.dp)
            ) {

            }
        }
    }
}

@Preview
@Composable
fun ShimmerItemPreview(){
    AnimatedShimmerItem()
}