package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.network.models.CompanyModel
import android.skills_market.data.network.models.VacancyModel
import android.skills_market.view_model.event.FavouritesEvent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface FavouritesUIState {
    data class Success(
        val favourites: List<VacancyModel>,
        val favouriteVacancy: VacancyModel
    ) : SearchUIState

    data object Error : FavouritesUIState
    data object Loading : FavouritesUIState
}

class FavouritesViewModel(
//    private val favouritesRepository: FavouritesRepository
) : ViewModel() {
    private val vacancies = listOf(
        VacancyModel(
            id = "0",
            position = "Педиатр",
            salary = 50000,
            company = CompanyModel("0", "Семейный доктор"),
            edArea = "Педиатрия",
            formOfEmployment = "Полная",
            requirements = "Диплом о законченом высшем образовании",
            location = "Казань",
            about = "",
            liked = true
        ),
        VacancyModel(
            id = "1",
            position = "Секретарь",
            salary = 20000,
            company = CompanyModel("1", "ИП Петров Игорь Михайлович"),
            edArea = "Юриспрюденция",
            formOfEmployment = "Полная",
            requirements = "Неполное высшее",
            location = "Саратов",
            about = " ",
            liked = true
        ),
        VacancyModel(
            id = "2",
            position = "Врач-терапевт",
            salary = 70000,
            company = CompanyModel("2", "АйБольно"),
            edArea = "Медицина",
            formOfEmployment = "Неполная",
            requirements = "Неполное высшее",
            location = "Вознесенск",
            about = " ",
            liked = true
        ),
    )

    private val tag = "VMTAG"
    private val _uiState = MutableStateFlow(
        FavouritesUIState.Success(
            vacancies,
            favouriteVacancy = vacancies[0]
        )
    )
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

    fun onEvent(event: FavouritesEvent) {
        when (event) {
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