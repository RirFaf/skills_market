package android.skills_market.view_models

import android.skills_market.network.models.StudentModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface MessengerUIState {
    data class Success(
        val enteredText: String = "",
        val isMsgBlank: Boolean = true,
    ) : MessengerUIState

    object Error : MessengerUIState
    object Loading : MessengerUIState
}

class MessengerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MessengerUIState.Success())
    val uiState: StateFlow<MessengerUIState> = _uiState.asStateFlow()
    var currentMessage by mutableStateOf("")
        private set

    fun updateEnteredText(enteredText: String) {
        currentMessage = enteredText
        _uiState.update { currentState ->
            currentState.copy(
                enteredText = currentMessage,
                isMsgBlank = currentMessage.isNotBlank()
            )
        }
    }

    fun sendMessage(messageText: String) {
        //Todo: вставить функцию для отправки сообщения на сервер
        _uiState.update { currentState ->
            currentState.copy(
                enteredText = "",
                isMsgBlank = true
            )
        }
    }

    fun receiveMessage(message: String) {

    }
}