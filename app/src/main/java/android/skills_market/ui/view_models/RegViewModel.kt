package android.skills_market.ui.view_models

import android.skills_market.ui.states.RegUIState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class RegViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegUIState())
}