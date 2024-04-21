package android.skills_market.view_model.event

sealed interface ProfileEvent {
    data class SetPhoneNumber(val input: String) : ProfileEvent
}