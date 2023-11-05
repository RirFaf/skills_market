package android.skills_market.view_models

import android.os.Parcelable
import android.skills_market.network.models.SelectedVacancyModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.parcelize.Parcelize

sealed interface VacancyUIState {
    data class Success(
        val vacancy: SelectedVacancyModel = SelectedVacancyModel(
            0, "", 0, "", "", "", "", ""
        ),
        val isResponded: Boolean = false,
    ) : VacancyUIState
    object Error: VacancyUIState
    object Loading: VacancyUIState
}

class VacancyViewModel : ViewModel() {
//    private val _uiState = MutableStateFlow(VacancyUIState.Success())
//    val uiState: StateFlow<VacancyUIState> = _uiState.asStateFlow()
//
//    fun setVacancy(providedVacancy: SelectedVacancyModel) {
//        _uiState.update { currentState ->
//            currentState.copy(vacancy = providedVacancy)
//        }
//    }
//
//    fun getVacancy(): SelectedVacancyModel {
//        return _uiState.value.vacancy
//    }
//
//    fun respond() {
//        _uiState.update { currentState ->
//            currentState.copy(isResponded = true)
//        }
//    }

    /*TODO: передача вакансии (
    *       отметка отклика
    *       ...
    * */
}