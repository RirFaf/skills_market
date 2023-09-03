package android.skills_market.view_models

import android.skills_market.view_models.states.MessengerUIState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MessengerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MessengerUIState())
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

    fun sendMessage(messageText: String){
        //Todo: вставить функцию для отправки сообщения на сервер
        _uiState.update { currentState ->
            currentState.copy(
                enteredText = "",
                isMsgBlank = true
            )
        }
    }

    fun receiveMessage(message: String){
        
    }
}