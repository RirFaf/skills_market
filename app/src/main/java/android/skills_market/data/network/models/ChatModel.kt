package android.skills_market.data.network.models

data class ChatModel(
    val vacancyId: String,
    val vacancyName: String,
    val studentId: String,
    val companyId: String,
    val companyName: String,
    val chatId: String = studentId+companyId+vacancyId,
    val messages: List<MessageModel> = listOf(),
)