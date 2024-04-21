package android.skills_market.view_model.event

import android.skills_market.network.models.VacancyModel

sealed interface ResponsesEvent {
    data object GetResponses : ResponsesEvent
    data class DeleteResponse(val input: VacancyModel) : ResponsesEvent
}