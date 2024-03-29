package android.skills_market.view_models

import android.skills_market.network.models.VacanciesModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface SearchUIState {
    data class Success(
        val vacancies: VacanciesModel
    ) : SearchUIState

    object Error : SearchUIState
    object Loading : SearchUIState

}

class SearchViewModel(
//    private val searchRepository: SearchRepository
) : ViewModel() {
        private val _uiState = MutableStateFlow(SearchUIState.Loading)
    val uiState: StateFlow<SearchUIState> = _uiState.asStateFlow()
//    var searchUIState: SearchUIState by mutableStateOf(SearchUIState.Loading)
//        private set

    init {
        getVacancies()
    }

    fun getVacancies() {
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

    fun respondToVacancy(){

    }

    fun getVacanciesBySearch() {}//TODO имплементировать поиск вакансий

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as DefaultApplication)
//                val searchRepository = application.container.searchRepository
//                SearchViewModel(searchRepository = searchRepository)
//            }
        }
    }
}