package android.skills_market.network.models

data class MessageModel(
    val text: String,
    val author: User,
) {
    val isFromMe: Boolean
        get() = author.id == "0"
}
