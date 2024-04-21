package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.network.SessionManager
import android.skills_market.view_model.event.ResumeEvent
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

sealed interface ResumeUIState {
    data class Success(
        val university: String = "",
        val faculty: String = "",
        val about: String = "",
        val experience: String = "",
        val course: String = "",
    ) : ResumeUIState

    object Error : ResumeUIState
    object Loading : ResumeUIState
}

class ResumeViewModel(
//    resumeRepository: ResumeRepository
) : ViewModel() {
    private val sessionManager = MutableLiveData<SessionManager>().value

    private val tag = "VMTAG"

    private val _uiState = MutableStateFlow(ResumeUIState.Success())
    val uiState: StateFlow<ResumeUIState.Success> = _uiState.asStateFlow()

    init {
        Log.i(tag, "ResumeViewModel initialized")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "ResumeViewModel is cleared")
    }

    fun onEvent(event: ResumeEvent){
        when(event){
            is ResumeEvent.SendInfo -> TODO()
            is ResumeEvent.SetAbout -> {
                _uiState.update {
                    it.copy(
                        about = event.input
                    )
                }
            }
            is ResumeEvent.SetCourse -> {
                _uiState.update {
                    it.copy(
                        course = event.input
                    )
                }
            }
            is ResumeEvent.SetExperience -> {
                _uiState.update {
                    it.copy(
                        experience = event.input
                    )
                }
            }
            is ResumeEvent.SetFaculty -> {
                _uiState.update {
                    it.copy(
                        faculty = event.input
                    )
                }
            }
            is ResumeEvent.SetUniversity -> {
                _uiState.update {
                    it.copy(
                        university = event.input
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
                ResumeViewModel(
//                    resumeRepository = resumeRepository
                )
            }
        }
    }
}