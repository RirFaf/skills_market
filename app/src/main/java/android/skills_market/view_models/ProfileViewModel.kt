package android.skills_market.view_models

import android.skills_market.network.models.StudentModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface ProfileUIState {
    data class Success(val student: StudentModel? = null) : ProfileUIState
    object Error : ProfileUIState
    object Loading : ProfileUIState
}

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUIState.Success())
    val uiState: StateFlow<ProfileUIState> = _uiState.asStateFlow()

}