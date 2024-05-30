package android.skills_market.view_model.event

sealed interface MessengerEvent {
    data object SendMessage : MessengerEvent

    data class GetMessages(
        val chatId: String,
        val companyName:String,
        val vacancyName:String,
        val onFailureAction: () -> Unit
    ) : MessengerEvent

    data class SetMessage(val input: String) : MessengerEvent
}