package android.skills_market.view_models

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface RedactorUIState {
    data class Success(
        val university: String = "",
        val faculty: String = "",
        val about: String = "",
        val experience: String = "",
        val course: String = "",
    ) : RedactorUIState

    object Error : RedactorUIState
    object Loading : RedactorUIState
}

class RedactorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RedactorUIState.Success())
    val uiState: StateFlow<RedactorUIState> = _uiState.asStateFlow()

    var university by mutableStateOf("")
        private set
    var faculty by mutableStateOf("")
        private set
    var about by mutableStateOf("")
        private set
    var experience by mutableStateOf("")
        private set
    var course by mutableStateOf("")
        private set

    fun updateUniversity(input: String){
        university = input
        _uiState.update {currentState ->
            currentState.copy(university = university)
        }
    }
    fun updateFaculty(input: String){
        faculty = input
        _uiState.update {currentState ->
            currentState.copy(faculty = faculty)
        }
    }
    fun updateAbout(input: String){
        about = input
        _uiState.update {currentState ->
            currentState.copy(about = about)
        }
    }
    fun updateExperience(input: String){
        experience = input
        _uiState.update {currentState ->
            currentState.copy(experience = experience)
        }
    }
    fun updateCourse(input: String){
        course = input
        _uiState.update {currentState ->
            currentState.copy(course = course)
        }
    }

    fun sendInfo(){
        //TODO
    }
}