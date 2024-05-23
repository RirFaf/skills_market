package android.skills_market.data.network.models

data class ChatModel(
    val vacancy: VacancyModel,
    val lastMessage: MessageModel,
) {
    val receiverId: String
        get() = vacancy.company.id
}