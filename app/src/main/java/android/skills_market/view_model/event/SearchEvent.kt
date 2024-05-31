package android.skills_market.view_model.event

import android.skills_market.data.network.models.VacancyFilter

sealed interface SearchEvent {
    data object GetVacancies : SearchEvent
    data class SetVacanciesFilter(val filter: VacancyFilter) : SearchEvent
    data class SetSearchInput(val input: String) : SearchEvent
    data class SetFrom(val input: String) : SearchEvent
    data class SetTo(val input: String) : SearchEvent
    data object ShowFilterDialog : SearchEvent
    data class ChangeLiked(val vacancyId: String) : SearchEvent

    data class RespondToVacancy(val vacancyId: String) : SearchEvent
}