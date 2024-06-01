package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.network.SessionManager
import android.skills_market.data.network.models.ResumeModel
import android.skills_market.data.repository.ResumeRepository
import android.skills_market.view_model.event.ResumeEvent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface ResumeUIState {
    data class Success(
        val resume: ResumeModel = ResumeModel(
            studentId = Firebase.auth.currentUser!!.uid,
            keySkills = "",
            salary = ""
        )
    ) : ResumeUIState

    data object Error : ResumeUIState
    data object Loading : ResumeUIState
}

class ResumeViewModel(
    private val resumeRepository: ResumeRepository
) : ViewModel() {
    private val tag = "VMTAG"

    private val _uiState = MutableStateFlow(ResumeUIState.Success())
    val uiState: StateFlow<ResumeUIState.Success> = _uiState.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "ResumeViewModel is cleared")
    }

    fun onEvent(event: ResumeEvent) {
        when (event) {
            is ResumeEvent.UpdateResumeInfo -> {
                resumeRepository.updateResume(
                    resume = _uiState.value.resume,
                    onFailureAction = {}
                )
            }

            is ResumeEvent.SetSalary -> {
                _uiState.update {
                    it.copy(
                        resume = _uiState.value.resume.copy(
                            salary = event.input
                        )
                    )
                }
            }

            is ResumeEvent.SetKeySkills -> {
                _uiState.update {
                    it.copy(
                        resume = _uiState.value.resume.copy(
                            keySkills = event.input
                        )
                    )
                }
            }
        }
    }

    init {
        resumeRepository.getResume(
            currentUserId = Firebase.auth.currentUser!!.uid,
            onSuccessAction = { resume ->
                _uiState.update {
                    it.copy(
                        resume = resume
                    )
                }
            },
            onFailureAction = {}
        )
        Log.i(tag, "ResumeViewModel initialized")
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DefaultApplication)
                val resumeRepository = application.container.resumeRepository
                ResumeViewModel(
                    resumeRepository = resumeRepository
                )
            }
        }
    }
}