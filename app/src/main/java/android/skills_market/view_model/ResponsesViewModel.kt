package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.network.models.VacanciesModel
import android.skills_market.data.network.models.VacancyModel
import android.skills_market.view_model.event.ResponsesEvent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface ResponsesUIState {
    data class Success(
        val responses: VacanciesModel,
        val selectedResponse: VacancyModel
    ) : ResponsesUIState

    data object Error : ResponsesUIState
    data object Loading : ResponsesUIState
}

class ResponsesViewModel(
    //responsesRepository: ResponsesRepository
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
        )
    )
    private val tag = "VMTAG"
    private val _uiState =
        MutableStateFlow(ResponsesUIState.Success(vacancies, vacancies.vacancies[0]))
    val uiState: StateFlow<ResponsesUIState.Success> = _uiState.asStateFlow()

    init {
        Log.i(
            tag, "ResponsesViewModel initialized"
        )
        onEvent(ResponsesEvent.GetResponses)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "ResponsesViewModel is cleared")
    }

    fun onEvent(event: ResponsesEvent) {
        when (event) {
            is ResponsesEvent.DeleteResponse -> {}//TODO
            is ResponsesEvent.GetResponses -> {}//TODO
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DefaultApplication)
//                val searchRepository = application.container.responsesRepository
//                val sessionManager = application.sessionManager
                ResponsesViewModel(
//                    responsesRepository = responsesRepository
                )
            }
        }
    }
}