package android.skills_market.view_models

import android.skills_market.database.SMFirebase
import android.skills_market.view_models.states.LoginUIState
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val db = SMFirebase()
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()
    var login by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set


    fun loginUser(
        onSuccessAction: () -> Unit,
        onWrongPasswordAction: () -> Unit,
        onWrongLoginAction: () -> Unit,
        login: String,
        password: String
    ) {
        if (checkLogin(login) && checkPassword(password)) {
            try {
                db.loginUser(onSuccessAction, login, password)
            } catch (e: com.google.firebase.auth.FirebaseAuthInvalidCredentialsException) {
                onWrongPasswordAction
            } catch (e: com.google.firebase.auth.FirebaseAuthInvalidUserException) {
                onWrongLoginAction
            }
        } else {
            Log.e("MyTag", "Here")
        }
    }

    private fun checkLogin(login: String): Boolean {
        _uiState.update { currentState ->
            currentState.copy(
                isLoginNull = login == ""
            )
        }
        return login !=""
    }

    private fun checkPassword(password: String): Boolean {
        _uiState.update { currentState ->
            currentState.copy(
                isPasswordNull = password == ""
            )
        }
        return password !=""
    }

    fun updateLogin(enteredLogin: String) {
        login = enteredLogin
    }

    fun updatePassword(enteredPassword: String) {
        password = enteredPassword
    }
}