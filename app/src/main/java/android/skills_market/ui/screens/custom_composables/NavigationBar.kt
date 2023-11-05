package android.skills_market.ui.screens.custom_composables

import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.theme.AccentBlue
import android.skills_market.ui.theme.Black
import android.skills_market.ui.theme.Gray90
import android.skills_market.ui.theme.White
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    BottomAppBar(
        backgroundColor = Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        list.forEach { listElement ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = listElement.icon!!),
                        contentDescription = listElement.title,
                        tint = if (currentRoute == listElement.route) AccentBlue else White
                    )
                },
                label = {
                    Text(
                        text = listElement.title!!,
                        fontSize = 9.sp,
                        color = if (currentRoute == listElement.route) AccentBlue else White
                    )
                },
                alwaysShowLabel = true,
                selected = currentRoute == listElement.route,
                onClick = {
                    navController.navigate(listElement.route) {
                        launchSingleTop = false
                        restoreState = true
                    }
                },
                enabled = currentRoute != listElement.route
            )
        }
    }
}

