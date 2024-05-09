package android.skills_market.view_model.event


sealed interface LoginEvent {
    data class SetLogin(val input: String) : LoginEvent
    data class SetPassword(val input: String) : LoginEvent
    data class LoginUser(
        val onSuccessAction: () -> Unit,
        val onFailureAction: () -> Unit,
        val onEmptyPasswordAction: () -> Unit,
        val onEmptyLoginAction: () -> Unit,
    ) : LoginEvent
}