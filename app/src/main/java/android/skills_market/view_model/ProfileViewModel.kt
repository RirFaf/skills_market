package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.network.SessionManager
import android.skills_market.data.network.models.StudentModel
import android.skills_market.data.network.models.UserAuthData
import android.skills_market.data.repository.ProfileRepository
import android.skills_market.view_model.event.ProfileEvent
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

sealed interface ProfileUIState {
    data class Success(
        val student: StudentModel = StudentModel(
            id = Firebase.auth.currentUser!!.uid,
            userAuthData = UserAuthData(
                "",
                Firebase.auth.currentUser!!.uid
            ),
            secondName = "",
            firstName = "",
            patronymicName = "",
            birthDate = "",
            university = "",
            institute = "",
            phoneNumber = "",
            aboutMe = "",
            gender = "",
            city = "",
            direction = "",
            course = ""
        )
    ) : ProfileUIState

    object Error : ProfileUIState
    object Loading : ProfileUIState
}

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val tag = "VMTAG"

    private val _uiState = MutableStateFlow(ProfileUIState.Success())
    val uiState: StateFlow<ProfileUIState.Success> = _uiState.asStateFlow()


    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "ProfileViewModel is cleared")
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.UpdateUserInfo -> {
                profileRepository.updateCurrentUserInfo(
                    user = _uiState.value.student,
                    onFailureAction = {}
                )
            }

            is ProfileEvent.SetCourse -> {
                _uiState.update {
                    it.copy(
                        student = _uiState.value.student.copy(
                            course = event.input
                        )
                    )
                }
            }
            is ProfileEvent.SetDirection -> {
                _uiState.update {
                    it.copy(
                        student = _uiState.value.student.copy(
                            direction = event.input
                        )
                    )
                }
            }
            is ProfileEvent.SetInstitute -> {
                _uiState.update {
                    it.copy(
                        student = _uiState.value.student.copy(
                            institute = event.input
                        )
                    )
                }
            }
            is ProfileEvent.SetPhoneNumber -> {
                _uiState.update {
                    it.copy(
                        student = _uiState.value.student.copy(
                            phoneNumber = event.input
                        )
                    )
                }
            }
            is ProfileEvent.SetUniversity -> {
                _uiState.update {
                    it.copy(
                        student = _uiState.value.student.copy(
                            university = event.input
                        )
                    )
                }
            }

            is ProfileEvent.SetAboutMe -> {
                _uiState.update {
                    it.copy(
                        student = _uiState.value.student.copy(
                            aboutMe = event.input
                        )
                    )
                }
            }
        }
    }

    init {
        profileRepository.getUserInfo(
            currentUserId = Firebase.auth.currentUser!!.uid,
            onSuccessAction = { user ->
                _uiState.update {
                    it.copy(
                        student = user
                    )
                }
            },
            onFailureAction = {}
        )
        Log.i(tag, "ProfileViewModel initialized")
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DefaultApplication)
                val profileRepository = application.container.profileRepository // TODO:
                ProfileViewModel(
                    profileRepository = profileRepository
                )
            }
        }
    }
}