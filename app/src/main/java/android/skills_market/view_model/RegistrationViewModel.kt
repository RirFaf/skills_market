package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.repository.RegistrationRepository
import android.skills_market.network.models.StudentModel
import android.skills_market.network.SMFirebase
import android.skills_market.network.models.requests.AuthRequest
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
        val patronymic: String = "",
        val name: String = "",
        val surname: String = "",
        val city: String = "",
        val course: String = "",
        val email: String = "",
        val password: String = "",
        val phone: String = "",
        val isPasswordBlank: Boolean = true,
        val isLoginBlank: Boolean = true,
    ) : RegUIState

    object Error : RegUIState
    object Loading : RegUIState
}

class RegViewModel(
    private val registrationRepository: RegistrationRepository
) : ViewModel() {
    private val tag = "VMTAG"
    private val db = SMFirebase()

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
                if (!_uiState.value.isLoginBlank && !_uiState.value.isPasswordBlank) {
                    db.addUser(
                        StudentModel(
                            _uiState.value.surname,
                            _uiState.value.name,
                            _uiState.value.patronymic,
                            _uiState.value.city,
                            _uiState.value.course,
                            _uiState.value.email,
                            _uiState.value.password,
                            _uiState.value.phone,
                        ),
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
                } else if (!uiState.value.isLoginBlank) {
                    event.onEmptyLoginAction()
                } else {
                    event.onEmptyPasswordAction()
                }
            }

            is RegistrationEvent.SetCity -> {
                _uiState.update {
                    it.copy(
                        city = event.input
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

            is RegistrationEvent.SetEmail -> {
                _uiState.update {
                    it.copy(
                        email = event.input,
                        isLoginBlank = event.input.isBlank()
                    )
                }
            }

            is RegistrationEvent.SetName -> {
                _uiState.update {
                    it.copy(
                        name = event.input
                    )
                }
            }

            is RegistrationEvent.SetPassword -> {
                _uiState.update {
                    it.copy(
                        password = event.input,
                        isPasswordBlank = event.input.isBlank()
                    )
                }
            }

            is RegistrationEvent.SetPatronymic -> {
                _uiState.update {
                    it.copy(
                        patronymic = event.input
                    )
                }
            }

            is RegistrationEvent.SetPhoneNumber -> {
                _uiState.update {
                    it.copy(
                        phone = event.input
                    )
                }
            }

            is RegistrationEvent.SetSurname -> {
                _uiState.update {
                    it.copy(
                        surname = event.input
                    )
                }
            }
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