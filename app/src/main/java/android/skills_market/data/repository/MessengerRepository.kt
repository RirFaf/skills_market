package android.skills_market.data.repository

import android.skills_market.data.constants.TAG
import android.skills_market.data.network.models.ChatModel
import android.skills_market.data.network.models.MessageModel
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface MessengerRepository {
    fun addChat(
        vacancyId: String,
        companyId: String,
        studentId: String,
    )

    fun getMessages(
        onDataChanged: (List<MessageModel>) -> Unit,
        currentChatId: String
    )

    fun sendMessage(
        message: String,
        currentChatId: String
    )

    fun getChats(
        onSuccessAction: (List<ChatModel>) -> Unit,
        onFailureAction: () -> Unit
    )
}

class FirebaseMessengerRepository(
):MessengerRepository{
    fun getTimeDate(timestamp: Long): String {
        return try {
            val netDate = Date(timestamp)
            val sfd = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
            sfd.format(netDate)
        } catch (e: Exception) {
            "date"
        }
    }

    override fun addChat(
        vacancyId: String,
        companyId: String,
        studentId: String
    ) {
        val likesRef = Firebase.database.getReference("messages1")
        val vacancyRef =
            Firebase.firestore.collection("vacancy")
                .whereEqualTo("id", vacancyId)
        var alreadyAdded = false
        likesRef
            .get()
            .addOnSuccessListener {
                for (data in it.children) {
                    if (data.child("chatId").value == studentId + companyId + vacancyId) {
                        alreadyAdded = true
                        break
                    }
                }
                if (!alreadyAdded) {
                    vacancyRef
                        .get()
                        .addOnSuccessListener { vacancySnapshot ->
                            likesRef
                                .push()
                                .setValue(
                                    ChatModel(
                                        vacancyId = vacancyId,
                                        vacancyName = vacancySnapshot.last().data["position"].toString(),
                                        companyId = companyId,
                                        companyName = vacancySnapshot.last().data["companyName"].toString(),
                                        studentId = studentId,
                                        messages = listOf(
                                            MessageModel("один", Firebase.auth.currentUser!!.uid, "0"),
                                            MessageModel("два", "debugCompanyId", "1"),
                                            MessageModel("три", Firebase.auth.currentUser!!.uid, "2"),
                                            MessageModel("четыре", "debugCompanyId", "3"),
                                        )
                                    )
                                )
                        }
                }
            }
            .addOnFailureListener {
                Log.e(TAG.FIREBASE, it.stackTraceToString())
            }
    }

    override fun getMessages(
        onDataChanged: (List<MessageModel>) -> Unit,
        currentChatId: String
    ) {
        val messagesRef = Firebase.database.getReference("messages1")
        val messagesListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val messages = ArrayList<MessageModel>()
                outerLoop@ for (chatSnapshot in snapshot.children) {
                    if (chatSnapshot.child("chatId").value == currentChatId) {
                        if (chatSnapshot.child("messages").exists()) {
                            for (messageSnapshot in chatSnapshot.child("messages").children) {
                                messages.add(
                                    MessageModel(
                                        text = messageSnapshot.child("text").value.toString(),
                                        senderId = messageSnapshot.child("senderId").value.toString(),
                                        timestamp = getTimeDate(
                                            messageSnapshot.child("timestamp").value.toString()
                                                .toLong()
                                        ),
                                    )
                                )
                            }
                        }
                        break@outerLoop
                    }
                }
                onDataChanged(messages)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG.FIREBASE, error.toException().stackTraceToString())
            }
        }
        messagesRef
            .addValueEventListener(messagesListener)
    }


    override fun sendMessage(
        message: String,
        currentChatId: String
    ) {
        val messagesRef = Firebase.database.getReference("messages1")
        messagesRef
            .get()
            .addOnSuccessListener {
                for (chat in it.children) {
                    if (chat.child("chatId").value == currentChatId) {
                        messagesRef
                            .child(chat.key.toString())
                            .child("messages")
                            .push()
                            .setValue(
                                MessageModel(
                                    text = message,
                                    senderId = Firebase.auth.currentUser!!.uid,
                                    timestamp = System.currentTimeMillis().toString()
                                )
                            )
                    }
                }
            }
    }

    override fun getChats(
        onSuccessAction: (List<ChatModel>) -> Unit,
        onFailureAction: () -> Unit
    ) {
        val chats = ArrayList<ChatModel>()
        val chatListRef =
            Firebase.database.getReference("messages1")
        chatListRef
            .get()
            .addOnSuccessListener {
                for (data in it.children) {
                    if (data.child("studentId").value == Firebase.auth.currentUser!!.uid) {
                        val lastMessage = data.child("messages").children.last()
                        chats.add(
                            ChatModel(
                                vacancyId = data.child("vacancyId").value.toString(),
                                vacancyName = data.child("vacancyName").value.toString(),
                                studentId = data.child("studentId").value.toString(),
                                companyName = data.child("companyName").value.toString(),
                                companyId = data.child("companyId").value.toString(),
                                messages = listOf(
                                    MessageModel(
                                        text = lastMessage.child("text").value.toString(),
                                        timestamp = getTimeDate(
                                            lastMessage.child("timestamp").value.toString().toLong()
                                        ),
                                        senderId = lastMessage.child("senderId").value.toString()
                                    )
                                ),
                            )
                        )
                    }
                }
                onSuccessAction(chats)
            }
            .addOnFailureListener {
                onFailureAction()
                Log.e(TAG.FIREBASE, it.stackTraceToString())
            }
    }
}