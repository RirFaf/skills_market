package android.skills_market.view_model.event


sealed interface LoginEvent {
    data class SetLogin(val input: String) : LoginEvent
    data class SetPassword(val input: String) : LoginEvent
    data class SetIsLoginCorrect(val input: Boolean) : LoginEvent
    data class SetIsPasswordCorrect(val input: Boolean) : LoginEvent
    data class SetIsLoginEntered(val input: Boolean) : LoginEvent
    data class SetIsPasswordEntered(val input: Boolean) : LoginEvent
    data class LoginUser(
        val onSuccessAction: () -> Unit,
        val onEmptyPasswordAction: () -> Unit,
        val onEmptyLoginAction: () -> Unit,
    ) : LoginEvent
}