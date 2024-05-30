package android.skills_market.data.network.models

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

data class MessageModel(
    val text: String,
    val senderId: String,
    val timestamp: String,
)