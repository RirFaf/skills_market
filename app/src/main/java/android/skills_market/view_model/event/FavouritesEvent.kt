package android.skills_market.view_model.event

sealed interface FavouritesEvent {
    data class RespondToVacancy(val vacancyId: String, val companyId: String) : FavouritesEvent
    data class ChangeLiked(val vacancyId: String) : FavouritesEvent
    data object GetLikedVacancies : FavouritesEvent
}