package android.skills_market.view_model

import android.skills_market.network.models.ShortVacancyModel
import androidx.lifecycle.ViewModel

sealed interface VacancyCardUIState{
    data class Success(
        val vacancy: ShortVacancyModel = ShortVacancyModel(
            0, "", 0, ""
        ),
        val isResponded: Boolean = false,
    ) : VacancyCardUIState
    object Error: VacancyCardUIState
    object Loading: VacancyCardUIState
}

class VacancyCardViewModel: ViewModel(){

}
