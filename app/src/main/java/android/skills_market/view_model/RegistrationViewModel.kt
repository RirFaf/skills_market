package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.repository.RegistrationRepository
import android.skills_market.data.network.models.StudentModel
import android.skills_market.data.network.SMFirebase
import android.skills_market.view_model.event.RegistrationEvent
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

sealed interface RegUIState {
    data class Success(
        val email: String = "android@mail.ru",
        val password: String = "123456",
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
    ) : RegUIState

    data object Error : RegUIState
    data object Loading : RegUIState
}

class RegViewModel(
    private val registrationRepository: RegistrationRepository
) : ViewModel() {
    private val tag = "VMTAG"
    private val db = SMFirebase

    private val _uiState = MutableStateFlow(RegUIState.Success())//переделать под обработку REST

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
                if (_uiState.value.email.isNotBlank() && _uiState.value.password.isNotBlank()) {
                    db.addUser(
                        login = _uiState.value.email,
                        password = _uiState.value.password,
                        onSuccessAction = {
                            event.onSuccessAction()
                        },
                        onFailureAction = {
                            event.onFailureAction()
                        }
                    )
//                    viewModelScope.launch{
//                        val response = registrationRepository.register(
//                            AuthRequest(
//                                email = uiState.value.email,
//                                password = uiState.value.password
//                            )
//                        )
//                        Log.i(tag, response.isExecuted.toString())
//                    }
                } else if (uiState.value.email.isBlank()) {
                    event.onEmptyLoginAction()
                    Log.d("FirebaseTag", "Login empty")

                } else {
                    event.onEmptyPasswordAction()
                    Log.d("FirebaseTag", "password empty")

                }
            }

            is RegistrationEvent.AddUserLocation -> TODO()
            is RegistrationEvent.AddUserName -> TODO()

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

            is RegistrationEvent.SetAboutMe -> TODO()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DefaultApplication)
                val registrationRepository = application.container.registrationRepository // TODO:
                val sessionManager = application.sessionManager
                RegViewModel(registrationRepository = registrationRepository)
            }
        }
    }
}