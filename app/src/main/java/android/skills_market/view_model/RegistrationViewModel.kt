package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.repository.RegistrationRepository
import android.skills_market.view_model.event.RegistrationEvent
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

sealed interface RegUIState {
    data class Success(
        val email: String = "",
        val password: String = "",
        val password1: String = "",
        val patronymicName: String = "",
        val firstName: String = "",
        val secondName: String = "",
        var birthDate: String = "",
        var university: String = "",
        var institute: String = "",
        var phoneNumber: String = "",
        var aboutMe: String = "",
        var gender: String = "",
        var city: String = "",
        var direction: String = "",
        var course: String = "",
    ) : RegUIState

    data object Error : RegUIState
    data object Loading : RegUIState
}

class RegViewModel(
    private val registrationRepository: RegistrationRepository
) : ViewModel() {
    private val tag = "VMTAG"

    private val _uiState = MutableStateFlow(RegUIState.Success())
    val uiState: StateFlow<RegUIState.Success> = _uiState.asStateFlow()

    init {
        Log.i(tag, "RegViewModel initialized")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "RegViewModel is cleared")
    }

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.AddUser -> {
                if (_uiState.value.email.isNotBlank() && _uiState.value.password.isNotBlank() && (_uiState.value.password == _uiState.value.password1)) {
                    viewModelScope.launch {
                        registrationRepository.register(
                            login = _uiState.value.email,
                            password = _uiState.value.password,
                            firstName = _uiState.value.firstName,
                            secondName = _uiState.value.secondName,
                            patronymicName = _uiState.value.patronymicName,
                            gender = _uiState.value.gender,
                            birthDate = _uiState.value.birthDate,
                            city = _uiState.value.city,
                            direction = _uiState.value.direction,
                            institute = _uiState.value.institute,
                            university = _uiState.value.university,
                            aboutMe = _uiState.value.aboutMe,
                            phoneNumber = _uiState.value.phoneNumber,
                            course = _uiState.value.course,
                            onFailureAction = {
                                event.onFailureAction()
                            },
                            onSuccessAction = {
                                event.onSuccessAction()
                            },
                        )
                    }
                }
                if (uiState.value.email.isBlank()) {
                    event.onEmptyLoginAction()
                    Log.d("FirebaseTag", "Login empty")

                }
                if (uiState.value.password.isBlank() || (_uiState.value.password != _uiState.value.password1)) {
                    event.onEmptyPasswordAction()
                    Log.d("FirebaseTag", "password empty")
                }
            }

            is RegistrationEvent.SetCity -> {
                _uiState.update {
                    it.copy(
                        city = event.input
                    )
                }
            }
            is RegistrationEvent.SetEmail -> {
                _uiState.update {
                    it.copy(
                        email = event.input,
                    )
                }
            }

            is RegistrationEvent.SetName -> {
                _uiState.update {
                    it.copy(
                        firstName = event.input
                    )
                }
            }

            is RegistrationEvent.SetPassword -> {
                _uiState.update {
                    it.copy(
                        password = event.input,
                    )
                }
            }
            is RegistrationEvent.SetPassword1 -> {
                _uiState.update {
                    it.copy(
                        password1 = event.input,
                    )
                }
            }

            is RegistrationEvent.SetPatronymic -> {
                _uiState.update {
                    it.copy(
                        patronymicName = event.input
                    )
                }
            }

            is RegistrationEvent.SetPhoneNumber -> {
                _uiState.update {
                    it.copy(
                        phoneNumber = event.input
                    )
                }
            }

            is RegistrationEvent.SetSurname -> {
                _uiState.update {
                    it.copy(
                        secondName = event.input
                    )
                }
            }

            is RegistrationEvent.SetDirection -> {
                _uiState.update {
                    it.copy(
                        direction = event.input
                    )
                }
            }

            is RegistrationEvent.SetGender -> {
                _uiState.update {
                    it.copy(
                        gender = event.input
                    )
                }
            }

            is RegistrationEvent.SetInstitute -> {
                _uiState.update {
                    it.copy(
                        institute = event.input
                    )
                }
            }

            is RegistrationEvent.SetBirthDate -> {
                _uiState.update {
                    it.copy(
                        birthDate = event.input
                    )
                }
            }

            is RegistrationEvent.SetUniversity -> {
                _uiState.update {
                    it.copy(
                        university = event.input
                    )
                }
            }

            is RegistrationEvent.SetAboutMe -> {
                _uiState.update {
                    it.copy(
                        aboutMe = event.input
                    )
                }
            }

            is RegistrationEvent.SetCourse -> {
                _uiState.update {
                    it.copy(
                        course = event.input
                    )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DefaultApplication)
                val registrationRepository = application.container.registrationRepository
                RegViewModel(registrationRepository = registrationRepository)
            }
        }
    }
}

