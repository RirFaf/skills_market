package android.skills_market.ui.screens

import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.screens.custom_composables.VacancyCard
import android.skills_market.view_model.FavouritesUIState
import android.skills_market.view_model.event.FavouritesEvent
import android.skills_market.view_model.event.SearchEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    state: FavouritesUIState.Success,
    navController: NavController,
    onEvent: (FavouritesEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Избранное") },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Show menu",
                        )
                    }
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Show menu",
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                itemsIndexed(
                    state.favourites
                ) { _, item ->
                    VacancyCard(
                        vacancy = item,
                        onClick = {
                            navController.navigate(
                                route = Screen.VacancyScreen.route +
                                        "/${item.id}" +
                                        "/${item.position}" +
                                        "/${item.salary}" +
                                        "/${item.company.id}" +
                                        "/${item.company.name}" +
                                        "/${item.edArea}" +
                                        "/${item.formOfEmployment}" +
                                        "/${item.requirements}" +
                                        "/${item.location}" +
                                        "/${item.about.ifEmpty { " " }}"+
                                        "/${item.liked}"
                            ) {
                                launchSingleTop = false
                                restoreState = true
                            }
                        },
                        onRespond = {},
                        onLike = {
                            onEvent(FavouritesEvent.ChangeLiked(item.id))
                        }
                    )
                }
            }
        }
    }
}