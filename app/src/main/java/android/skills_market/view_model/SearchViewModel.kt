package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.network.models.VacanciesModel
import android.skills_market.data.network.models.VacancyModel
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface SearchUIState {
    data class Success(
        val vacancies: VacanciesModel,
        val vacancy: VacancyModel = VacancyModel(
            id = 0,
            position = "",
            salary = 0,
            companyName = "",
            edArea = "",
            formOfEmployment = "",
            requirements = "",
            location = "",
            about = "",
        ),
        var currentSearch: String = ""
    ) : SearchUIState

    data object Error : SearchUIState
    data object Loading : SearchUIState

}

class SearchViewModel(
//    private val searchRepository: SearchRepository
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
            ),
            VacancyModel(
                position = "Уборщик",
                salary = 30000,
                companyName = "ЛангОфф",
                edArea = "Любая",
                formOfEmployment = "Неполная",
                requirements = "Нет",
                location = "Москва",
                about = " ",
            ),
            VacancyModel(
                position = "Пекарь",
                salary = 70000,
                companyName = "Кухня дяди Васи",
                edArea = "Нет",
                formOfEmployment = "Полная",
                requirements = "Нет",
                location = "Казань",
                about = " ",
            ),
        )
    )
    private val tag = "VMTAG"
    private val _uiState = MutableStateFlow(SearchUIState.Success(vacancies))
    val uiState: StateFlow<SearchUIState.Success> = _uiState.asStateFlow()

    init {
        Log.i(
            tag, "SearchViewModel initialized"
        )
        onEvent(SearchEvent.GetVacancies)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "SearchViewModel is cleared")
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.GetVacancies -> {
                viewModelScope.launch {
//            searchRepository.getVacanciesList().asResult()
//            _uiState = SearchUIState.Loading
//            _uiState = try {
//                SearchUIState.Success(searchRepository.getVacanciesList())
//            } catch (e: IOException) {
//                SearchUIState.Error
//            } catch (e: HttpException) {
//                SearchUIState.Error
//            }
                }
            }

            is SearchEvent.GetVacanciesBySearch -> {}//TODO имплементировать поиск вакансий
            is SearchEvent.RespondToVacancy -> {}
            is SearchEvent.Respond -> {}
            is SearchEvent.SetFavourite -> {}
            is SearchEvent.SetSearch -> {
                _uiState.update {
                    it.copy(
                        currentSearch = event.input
                    )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DefaultApplication)
//                val searchRepository = application.container.searchRepository
//                val sessionManager = application.sessionManager
                SearchViewModel(
//                    searchRepository = searchRepository
                )
            }
        }
    }
}