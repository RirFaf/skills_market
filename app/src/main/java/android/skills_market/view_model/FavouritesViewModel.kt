package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.network.models.VacanciesModel
import android.skills_market.network.models.VacancyModel
import android.skills_market.view_model.event.FavouritesEvent
import android.skills_market.view_model.event.SearchEvent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface FavouritesUIState {
    data class Success(
        val favourites: VacanciesModel,
        val favouriteVacancy: VacancyModel = VacancyModel(
            id = 0,
            position = "",
            salary = 0,
            companyName = "",
            edArea = "",
            formOfEmployment = "",
            requirements = "",
            location = "",
            about = "",
        )
    ) : SearchUIState

    object Error : FavouritesUIState
    object Loading : FavouritesUIState
}

class FavouritesViewModel(
//    private val favouritesRepository: FavouritesRepository
) : ViewModel() {
    private val vacancies = VacanciesModel(
        listOf(
            VacancyModel(
                id = 0,
                position = "Педиатр",
                salary = 50000,
                companyName = "Семейный доктор",
                edArea = "Педиатрия",
                formOfEmployment = "Полная",
                requirements = "Диплом о законченом высшем образовании",
                location = "Казань",
                about = "",
                liked = true
            ),
            VacancyModel(
                position = "Секретарь",
                salary = 20000,
                companyName = "ИП Петров Игорь Михайлович",
                edArea = "Юриспрюденция",
                formOfEmployment = "Полная",
                requirements = "Неполное высшее",
                location = "Саратов",
                about = " ",
                liked = true
            ),
            VacancyModel(
                position = "Врач-терапевт",
                salary = 70000,
                companyName = "АйБольно",
                edArea = "Медицина",
                formOfEmployment = "Неполная",
                requirements = "Неполное высшее",
                location = "Вознесенск",
                about = " ",
                liked = true
            ),
        )
    )
    private val tag = "VMTAG"
    private val _uiState = MutableStateFlow(FavouritesUIState.Success(vacancies))
    val uiState: StateFlow<FavouritesUIState.Success> = _uiState.asStateFlow()

    init {
        Log.i(
            tag, "FavouritesViewModel initialized"
        )
        onEvent(FavouritesEvent.GetVacancies)
    }
    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "FavouritesViewModel is cleared")
    }
    fun onEvent(event: FavouritesEvent){
        when(event){
            FavouritesEvent.GetVacancies -> {}//TODO
            FavouritesEvent.RespondToVacancy -> {}//TODO
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DefaultApplication)
//                val searchRepository = application.container.favouritesRepository
//                val sessionManager = application.sessionManager
                FavouritesViewModel(
//                    favouritesRepository = favouritesRepository
                )
            }
        }
    }
}