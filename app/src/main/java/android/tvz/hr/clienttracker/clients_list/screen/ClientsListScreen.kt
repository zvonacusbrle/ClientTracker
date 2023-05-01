package android.tvz.hr.clienttracker.clients_list.screen


import AppBottomNavigation
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.tvz.hr.clienttracker.clients_list.components.ClientCardItem
import android.tvz.hr.clienttracker.clients_list.components.ClientCardItemShimmerEffect
import android.tvz.hr.clienttracker.clients_list.viewmodel.ClientListViewModel
import android.tvz.hr.clienttracker.data.domain.model.Client
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import timber.log.Timber


@SuppressLint("UnrememberedMutableState")
@Composable
fun ClientsListScreen(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<ClientListViewModel>()

    val clientResponse = viewModel.clientResponse.value

    if (clientResponse.data.isNotEmpty()) {
        ClientItemList(clientResponse.data, navController)
    }

    if (clientResponse.error.isNotEmpty())
        Timber.tag(TAG).d("ClientsListScreen: ERROR")

    if (clientResponse.isLoading) {
        ClientCardItemShimmerEffect()
    }

}

@Composable
fun ClientItemList(data: List<Client>, navController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(data) { _, client ->
                ClientCardItem(client, navController)
            }
        }
        AppBottomNavigation(navController = navController)
    }
}



@Preview
@Composable
fun ClientCardPreview() {
    ClientCardItem(
        client = Client(
            1,
            "Pero",
            "https://img.mensxp.com/media/content/2018/Dec/the-king-ronnie-colemans-2018-documentary-is-quite-painful-to-watch-amp-yet-its-inspiring-1400x653-1545125563.jpg?w=820&h=540&cc=1"
        ),
        navController = rememberNavController()
    )
}

val CLIENT_ITEM_HEIGHT = 350.dp
val MEDIUM_PADDING = 15.dp