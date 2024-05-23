package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.repository.RegistrationRepository
import android.skills_market.data.network.SMFirebase
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
        val email: String = "android@mail.ru",
        val password: String = "123456",
        val password1: String = "123456",
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
                        db.addUser(
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

            is RegistrationEvent.AddUserLocation -> {
                if (_uiState.value.city.isNotBlank()
                    && _uiState.value.university.isNotBlank()
                    && _uiState.value.institute.isNotBlank()
                    && _uiState.value.direction.isNotBlank()
                ) {
                    viewModelScope.launch {
                        db.updateCurrentUserInfo(
                            city = _uiState.value.city,
                            university = _uiState.value.university,
                            institute = _uiState.value.institute,
                            direction = _uiState.value.direction,
                            onSuccessAction = { event.onSuccessAction() },
                            onFailureAction = { event.onFailureAction() }
                        )
                    }
                }
                if (_uiState.value.city.isBlank()) {
                    event.onEmptyCityAction()
                }
                if (_uiState.value.university.isBlank()) {
                    event.onEmptyUniversityAction()
                }
                if (_uiState.value.institute.isBlank()) {
                    event.onEmptyInstituteAction()
                }
                if (_uiState.value.direction.isBlank()) {
                    event.onEmptyDirectionAction()
                }
            }

            is RegistrationEvent.AddUserPersonalInfo -> {
                if (_uiState.value.firstName.isNotBlank()
                    && _uiState.value.secondName.isNotBlank()
                    && _uiState.value.patronymicName.isNotBlank()
                    && _uiState.value.gender.isNotBlank()
                    && _uiState.value.birthDate.isNotBlank()
                ) {
                    viewModelScope.launch {
                        db.updateCurrentUserInfo(
                            firstName = _uiState.value.firstName,
                            secondName = _uiState.value.secondName,
                            patronymicName = _uiState.value.patronymicName,
                            gender = _uiState.value.gender,
                            birthDate = _uiState.value.birthDate,
                            aboutMe = _uiState.value.aboutMe,
                            phoneNumber = if (_uiState.value.phoneNumber.length == 11) {
                                "+${_uiState.value.phoneNumber}"
                            } else {
                                ""
                            },
                            onSuccessAction = { event.onSuccessAction() },
                            onFailureAction = { event.onFailureAction() }
                        )
                    }
                }
                if (_uiState.value.firstName.isBlank()) {
                    event.onEmptyNameAction()
                }
                if (_uiState.value.secondName.isBlank()) {
                    event.onEmptySurnameAction()
                }
                if (_uiState.value.patronymicName.isBlank()) {
                    event.onEmptyPatronymicAction()
                }
                if (_uiState.value.gender.isBlank()) {
                    event.onEmptyGenderAction()
                }
                if (_uiState.value.birthDate.isBlank()) {
                    event.onEmptyBirthDateAction()
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

