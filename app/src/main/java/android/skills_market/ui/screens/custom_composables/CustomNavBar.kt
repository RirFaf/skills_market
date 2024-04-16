package android.skills_market.ui.screens.custom_composables

import android.skills_market.ui.navigation.Screen
import android.skills_market.ui.theme.md_theme_dark_surface
import android.util.Log
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun CustomNavBar(navController: NavController) {
    var currentIndex by remember {
        mutableStateOf(-1)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
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
        list.forEachIndexed() { index, element ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector =
                        //вся эта хрень для правильного отображения выбранного элемента
                        if ((currentIndex == index) && navBackStackEntry?.destination?.route == element.route) {
                            element.selectedIcon
                        } else {
                            if (currentIndex == -1 && navBackStackEntry?.destination?.route == Screen.ChatListScreen.route || navBackStackEntry?.destination?.route == Screen.SearchScreen.route) {
                                if (navBackStackEntry?.destination?.route == element.route) {
                                    element.selectedIcon
                                } else {
                                    element.unselectedIcon
                                }
                            } else {
                                if (currentIndex == index) {
                                    element.selectedIcon
                                } else {
                                    element.unselectedIcon
                                }
                            }
                        },
                        contentDescription = element.title,
                    )
                },
                label = {
                    Text(
                        text = element.title,
                    )
                },
                alwaysShowLabel = true,
                //вся эта хрень для правильного отображения выбранного элемента
                selected = if ((currentIndex == index) && navBackStackEntry?.destination?.route == element.route) {
                    true
                } else {
                    if (currentIndex == -1 && navBackStackEntry?.destination?.route == Screen.ChatListScreen.route || navBackStackEntry?.destination?.route == Screen.SearchScreen.route) {
                        navBackStackEntry?.destination?.route == element.route
                    } else {
                        currentIndex == index
                    }
                },
                onClick = {
                    navController.navigate(element.route) {
                        //Для обеспечения правильной обработки действия "назад" и выбора элементов
                        if (navBackStackEntry?.destination?.route == element.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                        launchSingleTop = false
                        restoreState = true
                    }
                    currentIndex = index
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

