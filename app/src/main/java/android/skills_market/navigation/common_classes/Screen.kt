package android.skills_market.navigation.common_classes

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object ChatListScreen : Screen("chat_list_screen")
    object ProfileScreen : Screen("profile_screen")
    object ResponsesListScreen : Screen("responses_list_screen")
    object SearchScreen : Screen("search_screen")
    object RegisterScreen : Screen("register_screen")
}