package android.skills_market.view_model

import android.skills_market.app.DefaultApplication
import android.skills_market.data.network.SessionManager
import android.skills_market.view_model.event.MessengerEvent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface MessengerUIState {
    data class Success(
        val enteredText: String = "",
    ) : MessengerUIState {
        val isMsgBlank: Boolean
            get() = enteredText.isBlank()
    }

    object Error : MessengerUIState
    object Loading : MessengerUIState
}

class MessengerViewModel(
//    private val messengerRepository: MessengerRepository
) : ViewModel() {
    private val sessionManager = MutableLiveData<SessionManager>().value

    private val tag = "VMTAG"

    private val _uiState = MutableStateFlow(MessengerUIState.Success())
    val uiState: StateFlow<MessengerUIState.Success> = _uiState.asStateFlow()

    init {
        Log.i(
            tag, "MessengerViewModel initialized"
        )
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "MessengerViewModel is cleared")
    }

    fun onEvent(event: MessengerEvent) {
        when (event) {
            is MessengerEvent.SendMessage -> {
                //TODO
            }

            is MessengerEvent.SetMessage -> {
                _uiState.update {
                    it.copy(
                        enteredText = event.input
                    )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DefaultApplication)
//                val messengerRepository = application.container.messengerRepository
                val sessionManager = application.sessionManager
                MessengerViewModel(
//                    messengerRepository = messengerRepository
                )
            }
        }
    }
}