package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.repository.LoginRepository
import android.skills_market.network.SMFirebase
import android.skills_market.network.SessionManager
import android.skills_market.network.models.requests.AuthRequest
import android.skills_market.view_model.event.LoginEvent
import android.util.Log
import androidx.lifecycle.MutableLiveData
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

sealed interface LoginUIState {
    data class Success(
        val isPasswordBlank: Boolean = true,
        val isPasswordEntered: Boolean = false,
        val isPasswordCorrect: Boolean = false,
        val isLoginEntered: Boolean = false,
        val isLoginBlank: Boolean = true,
        val isLoginCorrect: Boolean = false,
        val email: String = "",
        val password: String = "",
    ) : LoginUIState

    object Error : LoginUIState
    object Loading : LoginUIState
}

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val sessionManager = MutableLiveData<SessionManager>().value

    private val tag = "VMTAG"
    private val db = SMFirebase()

    private val _uiState = MutableStateFlow(LoginUIState.Success())//переделать под обработку REST

    val uiState: StateFlow<LoginUIState.Success> = _uiState.asStateFlow()

    init {
        Log.i(
            tag, "LoginViewModel initialized\n" +
                    "SessionManager ${sessionManager.toString()}"
        )
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
                            email = event.input
                        )
                    } else {
                        it.copy(
                            isLoginBlank = false,
                            email = event.input
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
                        login = uiState.value.email,
                        password = uiState.value.password
                    )
                    viewModelScope.launch{
                        val response = loginRepository.login(
                            AuthRequest(
                                email = uiState.value.email,
                                password = uiState.value.password
                            )
                        )
                        Log.i(tag, response.isExecuted.toString())
                    }
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
                val loginRepository = application.container.loginRepository // TODO:
                val sessionManager = application.sessionManager
                LoginViewModel(loginRepository = loginRepository)
            }
        }
    }
}