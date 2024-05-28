package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.network.SMFirebase
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
import kotlinx.coroutines.flow.update

sealed interface FavouritesUIState {
    data class Success(
        val favourites: List<VacancyModel> = listOf(),
    ) : SearchUIState

    data object Error : FavouritesUIState
    data object Loading : FavouritesUIState
}

class FavouritesViewModel(
//    private val favouritesRepository: FavouritesRepository
) : ViewModel() {
    private val tag = "VMTAG"
    private val _uiState = MutableStateFlow(
        FavouritesUIState.Success()
    )
    val uiState: StateFlow<FavouritesUIState.Success> = _uiState.asStateFlow()
    private val db = SMFirebase

    init {
        Log.i(
            tag, "FavouritesViewModel initialized"
        )
        onEvent(FavouritesEvent.GetLikedVacancies)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "FavouritesViewModel is cleared")
    }

    fun onEvent(event: FavouritesEvent) {
        when (event) {
            is FavouritesEvent.RespondToVacancy -> {}//TODO
            is FavouritesEvent.ChangeLiked -> {
                db.changeLiked(vacancyId = event.vacancyId, {}, {})
            }
            FavouritesEvent.GetLikedVacancies -> {
                db.getLikedVacancies(
                    onSuccessAction = { vacancies ->
                        _uiState.update {
                            it.copy(
                                favourites = vacancies
                            )
                        }
                    },
                    onFailureAction = {}
                )
            }
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