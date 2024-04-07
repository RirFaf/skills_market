package android.skills_market.view_model.event

sealed interface RegistrationEvent {
    data class SetSurname(val input: String) : RegistrationEvent
    data class SetName(val input: String) : RegistrationEvent
    data class SetPatronymic(val input: String) : RegistrationEvent
    data class SetCity(val input: String) : RegistrationEvent
    data class SetCourse(val input: String) : RegistrationEvent
    data class SetPhoneNumber(val input: String) : RegistrationEvent
    data class SetEmail(val input: String) : RegistrationEvent
    data class SetPassword(val input: String) : RegistrationEvent
    data class AddUser(
        val onSuccessAction: () -> Unit,
        val onFailureAction: () -> Unit,
        val onEmptyPasswordAction: () -> Unit,
        val onEmptyLoginAction: () -> Unit,
    ) : RegistrationEvent
}