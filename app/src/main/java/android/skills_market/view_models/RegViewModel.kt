package android.skills_market.view_models

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.skills_market.data.StudentModel
import android.skills_market.database.SMFirebase
import android.skills_market.ui.activities.AppActivity
import android.skills_market.view_models.states.RegUIState
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
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
        localContext: Context,
        onInvalidCredentialsAction: () -> Unit,
        onWeakPasswordAction: () -> Unit,
    ) {
        try {
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
                    (localContext as Activity).finish()
                    localContext.startActivity(
                        Intent(
                            localContext,
                            AppActivity::class.java
                        )
                    )
                }
            )
        } catch (e: FirebaseAuthWeakPasswordException) {
            onWeakPasswordAction()
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            onInvalidCredentialsAction()
        }
    }
}