package android.skills_market.ui.navigation

sealed class Screen(
    val route: String
) {
    /***Authentication***/
    data object LogRegScreen : Screen("log_reg_screen")
    data object RegistrationScreen : Screen("registration_screen")
    data object NameAndGenderScreen : Screen("name_and_gender_screen")
    data object CityCourseAndPhoneScreen : Screen("city_course_and_phone_screen")
    data object EmailAndPasswordScreen : Screen("email_and_password_screen")
    data object LoginScreen : Screen("login_screen")
    /***Main app***/
    data object SearchScreen : Screen("search_screen")
    data object VacancyScreen : Screen("vacancy_screen")
    data object FavouritesScreen : Screen("favourites_screen")
    data object ResponsesListScreen : Screen("responses_list_screen")
    /***Messenger***/
    data object ChatListScreen : Screen("chat_list_screen")
    data object MessengerScreen : Screen("messenger_screen")
    /***Profile***/
    data object ProfileScreen : Screen("profile_screen")
    data object ProfileRedactorScreen : Screen("profile_redactor_screen")
    /***Resume***/
    data object ResumeScreen: Screen("resume_screen")
    data object ResumeRedactorScreen: Screen("resume_redactor_screen")
}



