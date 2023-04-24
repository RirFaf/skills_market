package android.skills_market.custom_composables

import android.skills_market.navigation.common_classes.Screen
import android.skills_market.ui.theme.ButtonColor
import android.skills_market.ui.theme.TabBackgroundColor
import android.skills_market.ui.theme.WhiteFontColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

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
        backgroundColor = TabBackgroundColor
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        list.forEach { list ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = list.icon!!),
                        contentDescription = list.title,
                        tint = WhiteFontColor
                    )
                },
                label = {
                    Text(
                        text = list.title!!,
                        color = WhiteFontColor,
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

