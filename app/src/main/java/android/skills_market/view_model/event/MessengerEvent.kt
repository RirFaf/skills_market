package android.skills_market.view_model.event

sealed interface MessengerEvent {
    data class SetMessage(val input: String) : MessengerEvent
    data object SendMessage : MessengerEvent
}