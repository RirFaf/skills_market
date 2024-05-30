package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.constants.TAG
import android.skills_market.data.network.SMFirebase
import android.skills_market.data.network.models.CompanyModel
import android.skills_market.data.network.models.VacancyFilter
import android.skills_market.data.network.models.VacancyModel
import android.skills_market.data.repository.SearchRepository
import android.skills_market.view_model.event.SearchEvent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface SearchUIState {
    data class Success(
        val vacancies: List<VacancyModel> = listOf(),
        val searchInput: String = "",
        val currentFilter: VacancyFilter = VacancyFilter.None,
        val showFilterDialog: Boolean = false,
        val from: Int = 0,
        val to: Int = 0
    ) : SearchUIState

    data object Error : SearchUIState
    data object Loading : SearchUIState

}

class SearchViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val tag = "VMTAG"
    private val _uiState = MutableStateFlow(SearchUIState.Success())
    val uiState: StateFlow<SearchUIState.Success> = _uiState.asStateFlow()
    private val db = SMFirebase

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "SearchViewModel is cleared")
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.GetVacancies -> {
                searchRepository.getVacancies(
                    search = _uiState.value.searchInput,
                    filter = _uiState.value.currentFilter,
                    onSuccessAction = { vacancies ->
                        _uiState.update {
                            it.copy(
                                vacancies = vacancies
                            )
                        }
                    },
                    onFailureAction = {}
                )
            }

            is SearchEvent.RespondToVacancy -> {}
            is SearchEvent.SetFavourite -> {}
            is SearchEvent.SetSearchInput -> {
                _uiState.update {
                    it.copy(
                        searchInput = event.input
                    )
                }
            }

            is SearchEvent.SetFrom -> {
                _uiState.update {
                    it.copy(
                        from = if (event.input != "") {
                            event.input.toInt()
                        } else {
                            0
                        }
                    )
                }
            }

            is SearchEvent.SetTo -> {
                _uiState.update {
                    it.copy(
                        to = if (event.input != "") {
                            event.input.toInt()
                        } else {
                            0
                        }
                    )
                }
            }

            is SearchEvent.SetVacanciesFilter -> {
                _uiState.update {
                    it.copy(
                        currentFilter = event.filter
                    )
                }
            }

            is SearchEvent.ShowFilterDialog -> {
                _uiState.update {
                    it.copy(
                        showFilterDialog = !_uiState.value.showFilterDialog
                    )
                }
            }

            is SearchEvent.ChangeLiked -> {
                searchRepository.changeLiked(vacancyId = event.vacancyId, {})
            }
        }
    }


    init {
        Log.i(
            tag, "SearchViewModel initialized"
        )
        //ToDo добавить загрузочный экран
        onEvent(SearchEvent.GetVacancies)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DefaultApplication)
                val searchRepository = application.container.searchRepository
//                val sessionManager = application.sessionManager
                SearchViewModel(
                    searchRepository = searchRepository
                )
            }
        }
    }
}