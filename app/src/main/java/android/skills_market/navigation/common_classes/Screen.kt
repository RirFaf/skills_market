package android.skills_market.navigation.common_classes

import android.skills_market.R

sealed class Screen(val title: String?, val icon: Int?, val route: String) {
    object LoginScreen : Screen(null, null, "login_screen")
    object RegisterScreen : Screen(null, null, "register_screen")
    object SearchScreen : Screen("Поиск", R.drawable.baseline_search_24,"search_screen")
    object FavouritesScreen : Screen("Избранное", R.drawable.baseline_favorite_border_24,"favourites_screen")
    object ResponsesListScreen : Screen("Отклики", R.drawable.baseline_handshake_24,"responses_list_screen")
    object ChatListScreen : Screen("Чаты", R.drawable.baseline_chat_bubble_outline_24,"chat_list_screen")
    object ProfileScreen : Screen("Профиль", R.drawable.baseline_person_outline_24,"profile_screen")
}