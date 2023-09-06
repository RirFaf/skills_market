package android.skills_market.view_models.states

data class LoginUIState(
    val isPasswordNull: Boolean = true,
    val isPasswordCorrect: Boolean = false,
    val isLoginNull: Boolean = true,
    val isLoginCorrect: Boolean = false,
    val login: String = "",
    val password: String = "",
)