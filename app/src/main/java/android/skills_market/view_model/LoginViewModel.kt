package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.repository.LoginRepository
import android.skills_market.data.repository.SMFirebase
import android.skills_market.data.network.SessionManager
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
        val isPasswordCorrect: Boolean = false,//TODO переместить обработку ошибок в Error
        val isLoginCorrect: Boolean = false,//TODO переместить обработку ошибок в Error
        val email: String = "android@mail.ru",
        val password: String = "123456",
    ) : LoginUIState

    data object Error : LoginUIState
    data object Loading : LoginUIState
}

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val sessionManager = MutableLiveData<SessionManager>().value

    private val tag = "VMTAG"
    private val db = SMFirebase

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
            is LoginEvent.SetLogin -> {
                _uiState.update {
                    it.copy(
                        email = event.input
                    )
                }
            }

            is LoginEvent.SetPassword -> {
                _uiState.update {
                    it.copy(
                        password = event.input
                    )
                }
            }

            is LoginEvent.LoginUser -> {
                Log.d("MyTag", "LoginUser is called")
                if (_uiState.value.email.isNotBlank() && _uiState.value.password.isNotBlank()) {
                    viewModelScope.launch(){
                        loginRepository.login(
                            onSuccessAction = event.onSuccessAction,
                            onFailureAction = event.onFailureAction,
                            login = uiState.value.email,
                            password = uiState.value.password
                        )
                    }
                }
                if (uiState.value.email.isBlank()) {
                    event.onEmptyLoginAction()
                }
                if (uiState.value.password.isBlank()){
                    event.onEmptyPasswordAction()
                }
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