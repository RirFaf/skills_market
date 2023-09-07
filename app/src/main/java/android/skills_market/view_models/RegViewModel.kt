package android.skills_market.view_models

import android.skills_market.data.StudentModel
import android.skills_market.database.SMFirebase
import android.skills_market.view_models.states.RegUIState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegViewModel : ViewModel() {
    private val db = SMFirebase()
    private val _uiState = MutableStateFlow(RegUIState())
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


    fun updateName(enteredName: String) {
        name = enteredName
        _uiState.update { currentState ->
            currentState.copy(name = name)
        }
    }

    fun updateSurname(enteredSurname: String) {
        surname = enteredSurname
        _uiState.update { currentState ->
            currentState.copy(surname = surname)
        }
    }

    fun updatePatronymic(enteredPatronymic: String) {
        patronymic = enteredPatronymic
        _uiState.update { currentState ->
            currentState.copy(patronymic = patronymic)
        }
    }

    fun updateCity(enteredCity: String) {
        city = enteredCity
        _uiState.update { currentState ->
            currentState.copy(city = city)
        }
    }

    fun updateCourse(enteredCourse: String) {
        course = enteredCourse
        _uiState.update { currentState ->
            currentState.copy(course = course)
        }
    }

    fun updateEmail(enteredEmail: String) {
        email = enteredEmail
        _uiState.update { currentState ->
            currentState.copy(email = email)
        }
    }

    fun updatePassword(enteredPassword: String) {
        password = enteredPassword
        _uiState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    fun updatePhone(enteredPhone: String) {
        phone = enteredPhone
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