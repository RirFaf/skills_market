package android.skills_market.view_model.event

sealed interface MessengerEvent {
    data class SetMessage(val input: String) : MessengerEvent

    data class GetMessages(
        val receiverId: String,
        val onFailureAction: () -> Unit
    ) : MessengerEvent

    data class SendMessage(val input: String) : MessengerEvent
}