package android.skills_market.ui.navigation

import android.skills_market.R

sealed class Screen(val title: String?, val icon: Int?, val route: String) {
    object LogRegScreen : Screen(null, null, "log_reg_screen")
    object RegistrationScreen : Screen(null, null, "registration_screen")
    object NameAndGenderRegScreen : Screen(null, null, "name_and_gender_screen")
    object CityCourseAndPhone : Screen(null, null, "city_course_and_phone_screen")
    object EmailAndPasswordScreen : Screen(null, null, "email_and_password_screen")
    object LoginScreen : Screen(null, null, "login_screen")
    object SearchScreen : Screen("Поиск", R.drawable.search, "search_screen")
    object VacancyScreen : Screen(null, null, "vacancy_screen")
    object FavouritesScreen :
        Screen("Избранное", R.drawable.favorite_border, "favourites_screen")

    object ResponsesListScreen :
        Screen("Отклики", R.drawable.handshake, "responses_list_screen")

    object ChatListScreen :
        Screen("Чаты", R.drawable.chat_bubble_outline, "chat_list_screen")
    object MessengerScreen : Screen(null, null, "messenger_screen")


    object ProfileScreen :
        Screen("Профиль", R.drawable.person_outline, "profile_screen")
}