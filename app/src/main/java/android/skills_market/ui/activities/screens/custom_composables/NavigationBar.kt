package android.skills_market.ui.activities.screens.custom_composables.common

import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.theme.Gray900
import android.skills_market.ui.theme.Gray150
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
        list.forEach { listElement ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = listElement.icon!!),
                        contentDescription = listElement.title,
                        tint = Gray150
                    )
                },
                label = {
                    Text(
                        text = listElement.title!!,
                        color = Gray150,
                        fontSize = 9.sp
                    )
                },
                alwaysShowLabel = true,
                selected = currentRoute == listElement.route,
                onClick = {
                    navController.navigate(listElement.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                                inclusive = true
                            }
                        }
                        launchSingleTop = false
                        restoreState = true
                    }
                }
            )
        }
    }
}

