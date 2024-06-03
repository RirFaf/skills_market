package android.skills_market.ui.navigation

import android.skills_market.data.constants.ResponseStatus
import android.skills_market.data.network.models.CompanyModel
import kotlinx.serialization.Serializable

sealed class Screen(
) {
    /***Authentication***/
    @Serializable
    data object LogRegScreen:Screen()
    @Serializable
    data object RegistrationScreen:Screen()
    @Serializable
    data object PersonalDataScreen:Screen()
    @Serializable
    data object UniversityInfoScreen:Screen()
    @Serializable
    data object EmailAndPasswordScreen:Screen()
    @Serializable
    data object LoginScreen:Screen()
    /***Main app***/
    @Serializable
    data object SearchScreen:Screen()
    @Serializable
    data class VacancyScreen(
        val id: String,
        val companyId: String,
        val companyName: String,
        val position: String,
        val salary: Int,
        val edArea: String,
        val formOfEmployment: String,
        val requirements: String,
        val location: String,
        val about: String,
        var liked: Boolean = false,
        val responseStatus:String = ResponseStatus.EMPTY
    ):Screen()
    @Serializable
    data object FavouritesScreen:Screen()
    @Serializable
    data object ResponsesListScreen:Screen()
    /***Messenger***/
    @Serializable
    data object ChatListScreen:Screen()
    @Serializable
    data object MessengerScreen:Screen()
    @Serializable
    data object Messenger:Screen()
    /***Profile***/
    @Serializable
    data object Profile:Screen()
    @Serializable
    data object ProfileScreen:Screen()
    @Serializable
    data object ProfileRedactorScreen:Screen()
    /***Resume***/
    @Serializable
    data object Resume:Screen()
    @Serializable
    data object ResumeScreen:Screen()
    @Serializable
    data object ResumeRedactorScreen:Screen()
}