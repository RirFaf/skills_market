package android.skills_market.data.network.models

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

data class MessageModel(
    val text: String,
    val senderId: String,
    val time: String,
) {
    val isFromMe: Boolean
        get() = senderId == "0"
//        get() = senderId == Firebase.auth.currentUser?.uid
}
