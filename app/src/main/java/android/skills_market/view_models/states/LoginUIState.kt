package android.skills_market.view_models.states

data class LoginUIState(
    val login: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = true,
    val isPasswordNull: Boolean = true,
    val isLoginNull: Boolean = true,
)