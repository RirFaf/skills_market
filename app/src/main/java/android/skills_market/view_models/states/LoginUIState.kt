package android.skills_market.view_models.states

data class LoginUIState(
    val isPasswordBlank: Boolean = true,
    val isPasswordCorrect: Boolean = false,
    val isLoginBlank: Boolean = true,
    val isLoginCorrect: Boolean = false,
    val login: String = "",
    val password: String = "",
)