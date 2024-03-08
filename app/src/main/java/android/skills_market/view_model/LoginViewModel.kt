package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.network.SMFirebase
import android.skills_market.network.SessionManager
import android.skills_market.view_model.event.LoginEvent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface LoginUIState {
    data class Success(
        val isPasswordBlank: Boolean = true,
        val isPasswordEntered: Boolean = false,
        val isPasswordCorrect: Boolean = false,
        val isLoginEntered: Boolean = false,
        val isLoginBlank: Boolean = true,
        val isLoginCorrect: Boolean = false,
        val login: String = "",
        val password: String = "",
    ) : LoginUIState

    object Error : LoginUIState
    object Loading : LoginUIState
}

class LoginViewModel(
) : ViewModel() {
    private val sessionManager = MutableLiveData<SessionManager>().value
    private val tag = "VMTAG"
    private val db = SMFirebase()

    private val _uiState = MutableStateFlow(LoginUIState.Success())//переделать под обработку REST

    val uiState: StateFlow<LoginUIState.Success> = _uiState.asStateFlow()

    init {
        Log.i(tag, "LoginViewModel initialized\n" +
                "SessionManager ${sessionManager.toString()}")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "LoginViewModel is cleared")
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.SetIsLoginCorrect -> {
//                TODO()
            }

            is LoginEvent.SetIsPasswordCorrect -> {
//                TODO()
            }

            is LoginEvent.SetLogin -> {
                _uiState.update {
                    if (event.input.isBlank()) {
                        it.copy(
                            isLoginBlank = true,
                            login = event.input
                        )
                    } else {
                        it.copy(
                            isLoginBlank = false,
                            login = event.input
                        )
                    }
                }
            }

            is LoginEvent.SetPassword -> {
                _uiState.update {
                    if (event.input.isBlank()) {
                        it.copy(
                            isPasswordBlank = true,
                            password = event.input
                        )
                    } else {
                        it.copy(
                            isPasswordBlank = false,
                            password = event.input
                        )
                    }
                }
            }

            is LoginEvent.LoginUser -> {
                if (!_uiState.value.isLoginBlank && !_uiState.value.isPasswordBlank) {
                    db.loginUser(
                        onSuccessAction = event.onSuccessAction,
                        login = uiState.value.login,
                        password = uiState.value.password
                    )
                } else if (!uiState.value.isLoginBlank) {
                    event.onEmptyLoginAction()
                } else {
                    event.onEmptyPasswordAction()
                }
            }

            is LoginEvent.SetIsLoginEntered -> {
//                TODO()
            }

            is LoginEvent.SetIsPasswordEntered -> {
//                TODO()
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DefaultApplication)
                val loginRepository  = application.container.loginRepository // TODO:
                val sessionManager = application.sessionManager
                LoginViewModel()
            }
        }
    }
}