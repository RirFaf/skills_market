package android.skills_market.view_models.states

import android.skills_market.data.VacancyModel

data class VacancyUIState(
    val vacancy: VacancyModel? = null,
    val isResponded: Boolean = false
)
