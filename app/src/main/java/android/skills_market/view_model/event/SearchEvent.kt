package android.skills_market.view_model.event

sealed interface SearchEvent {
    data object RespondToVacancy : SearchEvent
    data object GetVacancies : SearchEvent
    data class GetVacanciesBySearch(val input: String) : SearchEvent
    data object SetFavourite : SearchEvent
    data class Respond(val url: String):SearchEvent
    data class SetSearch(val input: String): SearchEvent
}