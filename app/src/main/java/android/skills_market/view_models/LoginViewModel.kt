package android.skills_market.view_models

import android.content.Context
import android.skills_market.db_functions.SMFirebase
import android.skills_market.view_models.states.LoginUIState
import android.util.Log
import android.view.View
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.isActive
import kotlinx.coroutines.flow.update
import kotlin.math.log

class LoginViewModel : ViewModel() {
    private val db = SMFirebase()
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()
    var login by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set


    fun loginUser(isSuccessAction: () -> Unit, login: String, password: String) {
        if (checkLogin(login) && checkPassword(password)) {
            try {
                db.loginUser(isSuccessAction, login, password)
            } catch (e: com.google.firebase.auth.FirebaseAuthInvalidCredentialsException) {
                throw e
            } catch (e: com.google.firebase.auth.FirebaseAuthInvalidUserException){
                throw e
            }
        }
    }

    private fun checkLogin(login: String?): Boolean {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                isLoginNull = login == null
            )
        }
        return login != null
    }

    private fun checkPassword(password: String?): Boolean {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                isLoginNull = password == null
            )
        }
        return password != null
    }

    fun updateLogin(enteredLogin: String){
        login = enteredLogin
    }
    fun updatePassword(enteredPassword: String){
        password = enteredPassword
    }
}