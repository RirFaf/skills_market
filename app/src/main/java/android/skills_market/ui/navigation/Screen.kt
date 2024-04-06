package android.skills_market.ui.navigation

sealed class Screen(
    val route: String
) {
    object LogRegScreen : Screen("log_reg_screen")
    object RegistrationScreen : Screen("registration_screen")
    object NameAndGenderScreen : Screen("name_and_gender_screen")
    object CityCourseAndPhoneScreen : Screen("city_course_and_phone_screen")
    object EmailAndPasswordScreen : Screen("email_and_password_screen")
    object LoginScreen : Screen("login_screen")
    object SearchScreen : Screen("search_screen")
    object VacancyScreen : Screen("vacancy_screen")

    object FavouritesScreen : Screen("favourites_screen")

    object ResponsesListScreen : Screen("responses_list_screen")

    object ChatListScreen : Screen("chat_list_screen")

    object MessengerScreen : Screen("messenger_screen")
    object ProfileScreen : Screen("profile_screen")
    object ResumeRedactorScreen : Screen("resume_redactor_screen")
}