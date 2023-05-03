package android.skills_market.custom_composables

import android.skills_market.navigation.common_classes.Screen
import android.skills_market.ui.theme.Gray900
import android.skills_market.ui.theme.Gray150
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationBar(navController: NavController) {
    val list = listOf(
        Screen.SearchScreen,
        Screen.FavouritesScreen,
        Screen.ChatListScreen,
        Screen.ResponsesListScreen,
        Screen.ProfileScreen,
    )
    BottomNavigation(
        backgroundColor = Gray900
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        list.forEach { list ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = list.icon!!),
                        contentDescription = list.title,
                        tint = Gray150
                    )
                },
                label = {
                    Text(
                        text = list.title!!,
                        color = Gray150,
                        fontSize = 9.sp
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == list.route,
                onClick = {
                    navController.navigate(list.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

