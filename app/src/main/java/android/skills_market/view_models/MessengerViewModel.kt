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
    var message by mutableStateOf("")
        private set

    fun updateMessage(enteredText: String) {
        message = enteredText
        _uiState.update { currentState ->
            currentState.copy(message = enteredText)
        }
    }
}