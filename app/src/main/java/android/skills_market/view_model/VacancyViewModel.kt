package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.network.SessionManager
import android.skills_market.data.network.models.VacancyModel
import android.skills_market.view_model.event.ResumeEvent
import android.skills_market.view_model.event.VacancyEvent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface VacancyUIState {
    data class Success(
        val vacancy: VacancyModel = VacancyModel(-1, "", -1, "", "", "", "", "", "", false)
    ) : ResumeUIState

    data object Error : ResumeUIState
    data object Loading : ResumeUIState
}

class VacancyViewModel(
//    vacancyRepository: VacancyRepository
) : ViewModel() {
    private val sessionManager = MutableLiveData<SessionManager>().value

    private val tag = "VMTAG"

    private val _uiState = MutableStateFlow(VacancyUIState.Success())
    val uiState: StateFlow<VacancyUIState.Success> = _uiState.asStateFlow()

    init {
        Log.i(tag, "VacancyViewModel initialized")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "VacancyViewModel is cleared")
    }

    fun onEvent(event: VacancyEvent) {
        when (event) {
            is VacancyEvent.Like -> {}
            is VacancyEvent.Respond -> {}
            is VacancyEvent.SetVacancy -> {
                _uiState.update {
                    it.copy(
                        vacancy = event.vacancy,
                    )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DefaultApplication)
//                val vacancyRepository = application.container.vacancyRepository // TODO:
                val sessionManager = application.sessionManager
                VacancyViewModel(
//    vacancyRepository: VacancyRepository
                )
            }
        }
    }
}