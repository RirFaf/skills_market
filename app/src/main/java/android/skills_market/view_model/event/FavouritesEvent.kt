package android.skills_market.view_model.event

sealed interface    FavouritesEvent {
    data object RespondToVacancy: FavouritesEvent
    data class ChangeLiked(val vacancyId:String):FavouritesEvent
    data object GetLikedVacancies : FavouritesEvent
}