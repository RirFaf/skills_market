package android.skills_market.view_models

import android.skills_market.network.SMFirebase
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface LoginUIState {
    data class Success(
        val isPasswordBlank: Boolean = true,
        val isPasswordCorrect: Boolean = false,
        val isLoginBlank: Boolean = true,
        val isLoginCorrect: Boolean = false,
        val login: String = "",
        val password: String = "",
    ) : LoginUIState

    object Error : LoginUIState
    object Loading : LoginUIState
}

class LoginViewModel : ViewModel() {
    private val db = SMFirebase()
    private val _uiState = MutableStateFlow(LoginUIState.Success())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    var login by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set


    fun loginUser(
        onSuccessAction: () -> Unit,
        onEmptyPasswordAction: () -> Unit,
        onEmptyLoginAction: () -> Unit,
    ) {
        if (!isLoginBlankOrNull(login) && !isPasswordBlankOrNull(password)) {
            db.loginUser(onSuccessAction, login, password)
        } else if (isLoginBlankOrNull(login)) onEmptyLoginAction() else onEmptyPasswordAction()
    }

    private fun isLoginBlankOrNull(login: String): Boolean {
        _uiState.update { currentState ->
            currentState.copy(
                isLoginBlank = login.isBlank(),
                isLoginCorrect = false
            )
        }
        return login.isBlank()
    }

    private fun isPasswordBlankOrNull(password: String): Boolean {
        _uiState.update { currentState ->
            currentState.copy(
                isPasswordBlank = password.isBlank(),
                isPasswordCorrect = false
            )
        }
        return password.isBlank()
    }

    fun updateLogin(enteredLogin: String) {
        login = enteredLogin
        _uiState.update { currentState ->
            currentState.copy(
                login = login
            )
        }
    }

    fun updatePassword(enteredPassword: String) {
        password = enteredPassword
        _uiState.update { currentState ->
            currentState.copy(
                password = password
            )
        }
    }
}