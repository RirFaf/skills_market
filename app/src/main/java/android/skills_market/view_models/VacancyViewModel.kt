package android.skills_market.view_models

import android.skills_market.view_models.states.VacancyUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VacancyViewModel {
    private val _uiState = MutableStateFlow(VacancyUIState())
    val uiState: StateFlow<VacancyUIState> = _uiState.asStateFlow()

    /*TODO: передача вакансии (
    *       отметка отклика
    *       ...
    * */
}