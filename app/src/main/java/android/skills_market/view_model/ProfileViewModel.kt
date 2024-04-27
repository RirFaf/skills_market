package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.network.SessionManager
import android.skills_market.data.network.models.StudentModel
import android.skills_market.view_model.event.ProfileEvent
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

sealed interface ProfileUIState {
    data class Success(val student: StudentModel? = null) : ProfileUIState
    object Error : ProfileUIState
    object Loading : ProfileUIState
}

class ProfileViewModel : ViewModel() {
    private val sessionManager = MutableLiveData<SessionManager>().value

    private val tag = "VMTAG"

    private val _uiState = MutableStateFlow(ProfileUIState.Success())
    val uiState: StateFlow<ProfileUIState.Success> = _uiState.asStateFlow()

    init {
        Log.i(tag, "ProfileViewModel initialized")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "ProfileViewModel is cleared")
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.SetPhoneNumber -> {
                _uiState.update {
                    it.copy(
                        student = it.student?.copy(phone = event.input)
                    )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DefaultApplication)
//                val resumeRepository = application.container.resumeRepository // TODO:
                val sessionManager = application.sessionManager
                ProfileViewModel(
//                    resumeRepository = resumeRepository
                )
            }
        }
    }
}