package android.skills_market.ui.screens.custom_composables

import android.skills_market.ui.navigation.Screen
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            screen = Screen.SearchScreen,
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            hasNews = false,
            onClick = {
                navController.navigate(Screen.SearchScreen)
            }
        ),
        BottomNavItem(
            title = "Избранное",
            screen = Screen.FavouritesScreen,
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            hasNews = false,
            onClick = {
                navController.navigate(Screen.FavouritesScreen)
            }
        ),
        BottomNavItem(
            title = "Отклики",
            screen = Screen.ResponsesListScreen,
            selectedIcon = Icons.Filled.ThumbUp,
            unselectedIcon = Icons.Outlined.ThumbUp,
            hasNews = false,
            onClick = {
                navController.navigate(Screen.ResponsesListScreen)
            }
        ),
        BottomNavItem(
            title = "Чаты",
            screen = Screen.Messenger,
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
            hasNews = false,
            onClick = {
                navController.navigate(Screen.ChatListScreen)
            }
        ),
        BottomNavItem(
            title = "Профиль",
            screen = Screen.ProfileScreen,
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = false,
            onClick = {
                navController.navigate(Screen.ProfileScreen)
            }
        ),
    )
    NavigationBar {
        list.forEachIndexed() { index, element ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector =
//                        //TODO переделать
                        if ((currentIndex == index) &&
                            navBackStackEntry?.destination?.route?.split(".")?.last()!! == element.screen.toString()) {
                            element.selectedIcon
                        } else {
                            if (currentIndex == -1 &&
                                navBackStackEntry?.destination?.route?.split(".")?.last() == Screen.ChatListScreen.toString() ||
                                navBackStackEntry?.destination?.route?.split(".")?.last() == Screen.SearchScreen.toString()) {
                                if (navBackStackEntry?.destination?.route?.split(".")?.last() == element.screen.toString()) {
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
                selected =
                if ((currentIndex == index) &&
                    navBackStackEntry?.destination?.route?.split(".")?.last() == element.screen.toString()
                ) {
                    true
                } else {
                    if (currentIndex == -1 &&
                        navBackStackEntry?.destination?.route?.split(".")?.last() == Screen.ChatListScreen.toString() ||
                        navBackStackEntry?.destination?.route?.split(".")?.last() == Screen.SearchScreen.toString()
                    ) {
                        navBackStackEntry?.destination?.route?.split(".")?.last() == element.screen.toString()
                    } else {
                        currentIndex == index
                    }
                },
                onClick = {
                    navController.navigate(element.screen) {
                        //Для обеспечения правильной обработки действия "назад" и выбора элементов
                        if (navBackStackEntry?.destination?.route?.split(".")?.last() == element.screen.toString()) {
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
    val screen: Screen,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val onClick: () -> Unit
)

