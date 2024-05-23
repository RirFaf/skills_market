package android.skills_market.view_model.event

sealed interface    FavouritesEvent {
    data object RespondToVacancy: FavouritesEvent
    data object GetVacancies : FavouritesEvent
}