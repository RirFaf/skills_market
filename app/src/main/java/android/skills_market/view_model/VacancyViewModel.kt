package android.skills_market.view_model

import android.skills_market.network.models.SelectedVacancyModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed interface VacancyUIState {
    data class Success(
        val vacancy: SelectedVacancyModel = SelectedVacancyModel(
            0, "", 0, "", "", "", "", "", ""
        ),
        val isResponded: Boolean = false,
    ) : VacancyUIState

    object Error : VacancyUIState
    object Loading : VacancyUIState
}

class VacancyViewModel : ViewModel() {
    var vacancyUIState: VacancyUIState by mutableStateOf(VacancyUIState.Loading)
        private set

    fun getVacancy() {
        viewModelScope.launch {
            vacancyUIState = VacancyUIState.Loading
            Thread.sleep(1000)
            vacancyUIState = VacancyUIState.Success()
        }
    }
}