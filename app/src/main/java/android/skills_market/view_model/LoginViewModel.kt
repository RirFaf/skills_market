package android.skills_market.view_model

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.skills_market.activities.AppActivity
import android.skills_market.network.SMFirebase
import android.skills_market.view_model.event.LoginEvent
import android.util.Log
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
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
    val tag = "VMTAG"
    private val db = SMFirebase()

    private val _uiState = MutableStateFlow(LoginUIState.Success())

    val uiState: StateFlow<LoginUIState.Success> = _uiState.asStateFlow()

    init {
        Log.i(tag, "LoginViewModel initialized")
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
                if (!uiState.value.isLoginBlank && !uiState.value.isPasswordBlank) {
                    db.loginUser(
                        event.onSuccessAction,
                        uiState.value.login,
                        uiState.value.password
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
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                val savedStateHandle = extras.createSavedStateHandle()
                return LoginViewModel(
                ) as T
            }
        }
    }
}