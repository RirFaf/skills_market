package android.skills_market.view_model.event

import android.skills_market.data.network.models.VacancyModel

sealed interface VacancyEvent {
    data class Respond(val vacancyId: Int) : VacancyEvent
    data class ChangeLiked(val vacancyId: String) : VacancyEvent
    data class SetVacancy(val vacancy: VacancyModel) : VacancyEvent
}