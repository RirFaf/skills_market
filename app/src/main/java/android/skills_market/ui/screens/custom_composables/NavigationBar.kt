package android.skills_market.ui.screens.custom_composables

import android.skills_market.ui.navigation.Screen
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun CustomNavBar(navController: NavController) {
    val list = listOf(
        BottomNavItem(
            title = "Поиск",
            route = Screen.SearchScreen.route,
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            hasNews = false,
            onClick = {
                navController.navigate(Screen.SearchScreen.route)
            }
        ),
        BottomNavItem(
            title = "Избранное",
            route = Screen.FavouritesScreen.route,
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            hasNews = false,
            onClick = {
                navController.navigate(Screen.FavouritesScreen.route)
            }
        ),
        BottomNavItem(
            title = "Отклики",
            route = Screen.ResponsesListScreen.route,
            selectedIcon = Icons.Filled.ThumbUp,
            unselectedIcon = Icons.Outlined.ThumbUp,
            hasNews = false,
            onClick = {
                navController.navigate(Screen.ResponsesListScreen.route)
            }
        ),
        BottomNavItem(
            title = "Чаты",
            route = Screen.ChatListScreen.route,
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
            hasNews = false,
            onClick = {
                navController.navigate(Screen.ChatListScreen.route)
            }
        ),
        BottomNavItem(
            title = "Профиль",
            route = Screen.ProfileScreen.route,
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = false,
            onClick = {
                navController.navigate(Screen.ProfileScreen.route)
            }
        ),
    )
    NavigationBar {
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
        list.forEachIndexed() { index, listElement ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (selectedItemIndex == index) {
                            listElement.selectedIcon
                        } else {
                            listElement.unselectedIcon
                        },
                        contentDescription = listElement.title,
                    )
                },
                label = {
                    Text(
                        text = listElement.title,
                    )
                },
                alwaysShowLabel = true,
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(listElement.route) {
                        launchSingleTop = false
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val onClick: () -> Unit
)

