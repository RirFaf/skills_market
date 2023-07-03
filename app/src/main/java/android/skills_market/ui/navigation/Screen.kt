package android.skills_market.ui.navigation

import android.skills_market.R

sealed class Screen(val title: String?, val icon: Int?, val route: String) {
    object LogRegScreen : Screen(null, null, "log_reg_screen")
    object LoginScreen : Screen(null, null, "login_screen")
    object RegistrationScreen : Screen(null, null, "registration_screen")
    object NameAndGenderRegScreen : Screen(null, null, "name_and_gender_screen")
    object CityCourseAndPhone : Screen(null, null, "city_course_and_phone_screen")
    object EmailAndPasswordScreen : Screen(null, null, "email_and_password_screen")
    object SearchScreen : Screen("Поиск", R.drawable.baseline_search_outline_24, "search_screen")
    object FavouritesScreen :
        Screen("Избранное", R.drawable.baseline_favorite_border_24, "favourites_screen")

    object ResponsesListScreen :
        Screen("Отклики", R.drawable.baseline_handshake_24, "responses_list_screen")

    object ChatListScreen :
        Screen("Чаты", R.drawable.baseline_chat_bubble_outline_24, "chat_list_screen")

    object ProfileScreen :
        Screen("Профиль", R.drawable.baseline_person_outline_24, "profile_screen")
}