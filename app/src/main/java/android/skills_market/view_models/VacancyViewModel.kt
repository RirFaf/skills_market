package android.skills_market.view_models

import android.os.Parcelable
import android.skills_market.data.VacancyModel
import android.skills_market.view_models.states.VacancyUIState
import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Parcelize
class VacancyViewModel: ViewModel(),Parcelable {
    private val _uiState = MutableStateFlow(VacancyUIState())
    val uiState: StateFlow<VacancyUIState> = _uiState.asStateFlow()


    fun setVacancy(providedVacancy: VacancyModel) {
        _uiState.update { currentState ->
            currentState.copy(vacancy = providedVacancy)
        }
    }

    fun getVacancy(): VacancyModel {
        return _uiState.value.vacancy
    }

    fun respond() {
        _uiState.update { currentState ->
            currentState.copy(isResponded = true)
        }
    }

    /*TODO: передача вакансии (
    *       отметка отклика
    *       ...
    * */
}