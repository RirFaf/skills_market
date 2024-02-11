package android.skills_market.view_model

import android.skills_market.network.models.StudentModel
import android.skills_market.network.SMFirebase
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

class RegViewModel : ViewModel() {
    private val db = SMFirebase()
    private val _uiState = MutableStateFlow(RegUIState.Success())
    val uiState: StateFlow<RegUIState> = _uiState.asStateFlow()

    var surname by mutableStateOf("")
        private set
    var name by mutableStateOf("")
        private set
    var patronymic by mutableStateOf("")
        private set
    var city by mutableStateOf("")
        private set
    var course by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var phone by mutableStateOf("")
        private set


    fun updateName(input: String) {
        name = input
        _uiState.update { currentState ->
            currentState.copy(name = name)
        }
    }

    fun updateSurname(input: String) {
        surname = input
        _uiState.update { currentState ->
            currentState.copy(surname = surname)
        }
    }

    fun updatePatronymic(input: String) {
        patronymic = input
        _uiState.update { currentState ->
            currentState.copy(patronymic = patronymic)
        }
    }

    fun updateCity(input: String) {
        city = input
        _uiState.update { currentState ->
            currentState.copy(city = city)
        }
    }

    fun updateCourse(input: String) {
        course = input
        _uiState.update { currentState ->
            currentState.copy(course = course)
        }
    }

    fun updateEmail(input: String) {
        email = input
        _uiState.update { currentState ->
            currentState.copy(email = email)
        }
    }

    fun updatePassword(input: String) {
        password = input
        _uiState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    fun updatePhone(input: String) {
        phone = input
        _uiState.update { currentState ->
            currentState.copy(phone = phone)
        }
    }

    fun register(
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
        onEmptyPasswordAction: () -> Unit,
        onEmptyLoginAction: () -> Unit,
    ) {
        if (!isLoginBlankOrNull(email) && !isPasswordBlankOrNull(password)) {
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
                    onSuccessAction()
                },
                onFailureAction = {
                    onFailureAction()
                }
            )
        } else if (isLoginBlankOrNull(email)) onEmptyLoginAction() else onEmptyPasswordAction()
    }

    private fun isLoginBlankOrNull(login: String): Boolean {
        _uiState.update { currentState ->
            currentState.copy(
                isLoginBlank = login.isBlank(),
            )
        }
        return login.isBlank()
    }

    private fun isPasswordBlankOrNull(password: String): Boolean {
        _uiState.update { currentState ->
            currentState.copy(
                isPasswordBlank = password.isBlank(),
            )
        }
        return password.isBlank()
    }
}