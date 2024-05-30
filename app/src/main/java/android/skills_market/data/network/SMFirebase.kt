package android.skills_market.data.network

import android.skills_market.data.constants.TAG
import android.skills_market.data.network.models.ChatModel
import android.skills_market.data.network.models.CompanyModel
import android.skills_market.data.network.models.MessageModel
import android.skills_market.data.network.models.StudentModel
import android.skills_market.data.network.models.UserAuthData
import android.skills_market.data.network.models.VacancyFilter
import android.skills_market.data.network.models.VacancyModel
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore

object SMFirebase {
    private val auth = Firebase.auth
    private val currentUserId = Firebase.auth.currentUser?.uid

    fun changeLiked(
        vacancyId: String,
        onFailureAction: () -> Unit
    ) {
        val likesRef = Firebase.database.getReference("likes").child(Firebase.auth.currentUser!!.uid)
        var alreadyAdded = false
        likesRef
            .get()
            .addOnSuccessListener {
                for (data in it.children) {
                    if (data.value == vacancyId) {
                        alreadyAdded = true
                        data.ref.removeValue()
                        break
                    }
                }
                if (!alreadyAdded) {
                    likesRef
                        .push()
                        .setValue(vacancyId)
                }
            }
            .addOnFailureListener {
                onFailureAction()
                Log.e(TAG.FIREBASE, it.stackTraceToString())
            }
    }

    fun updateCurrentUserInfo(
        secondName: String = "",
        firstName: String = "",
        patronymicName: String = "",
        birthDate: String = "",
        university: String = "",
        institute: String = "",
        phoneNumber: String = "",
        aboutMe: String = "",
        gender: String = "",
        city: String = "",
        direction: String = "",
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit
    ) {
        val rootRef = Firebase.firestore.collection("users")
        auth.currentUser?.let { currentUser ->

            rootRef.document(
                rootRef.whereEqualTo("id", currentUser.uid).get().result.documents[0].id
            ).update(
                mapOf(
                    "userAuthData.id" to currentUser.uid,
                    "userAuthData.email" to rootRef.whereEqualTo("id", currentUser.uid)
                        .get().result.documents[0].data?.get("email"),
                    "firstName" to firstName,
                    "secondName" to secondName,
                    "patronymicName" to patronymicName,
                    "birthDate" to birthDate,
                    "university" to university,
                    "institute" to institute,
                    "phoneNumber" to phoneNumber,
                    "aboutMe" to aboutMe,
                    "gender" to gender,
                    "city" to city,
                    "direction" to direction,
                    "id" to currentUser.uid
                )
            )
                .addOnFailureListener {
                    Log.e(TAG.FIREBASE, it.toString())
                }
                .addOnSuccessListener {
                    Log.d(TAG.FIREBASE, "User info updated")
                }
        }
    }

    fun logoutUser(
        onLogoutAction: () -> Unit
    ) {
        auth.signOut()
        onLogoutAction()
    }

    fun authenticateUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    fun getMessages(
        receiverId: String,
        onFailureAction: () -> Unit
    ): List<MessageModel> {
        val currentUserId = Firebase.auth.currentUser?.uid
        val currentChatRef =
            Firebase.database.getReference("messages").child("$currentUserId$receiverId")
                .orderByChild("time")
        val messages = ArrayList<MessageModel>()
        for (chatSnapshot in currentChatRef.get().result.children) {
            try {
                messages.add(
                    MessageModel(
                        text = chatSnapshot.child("text").getValue(String::class.java)!!,
                        senderId = chatSnapshot.child("senderId").getValue(String::class.java)!!,
                        time = chatSnapshot.child("time").getValue(String::class.java)!!,
                    )
                )
            } catch (e: Exception) {
                onFailureAction()
                Log.e(TAG.FIREBASE, e.toString())
            }
        }
        return messages
    }

    fun getChats(
        onFailureAction: () -> Unit
    ): List<ChatModel> {
        val chats = ArrayList<ChatModel>()
        val currentUserId = Firebase.auth.currentUser?.uid
        val chatListRef =
            Firebase.database.getReference("messages").get().result.children
        val vacanciesRef = Firebase.firestore.collection("vacancy")
        var vacancy = VacancyModel()
        for (chatListSnapshot in chatListRef) {
            try {
                vacanciesRef.document(
                    vacanciesRef.whereEqualTo("id", chatListSnapshot.child("vacancyId"))
                        .get().result.documents[0].id
                ).get().addOnSuccessListener { snapshot ->
                    vacancy = snapshot.toObject(VacancyModel::class.java)!!
                }
                chats.add(
                    ChatModel(
                        vacancy = vacancy,
                        lastMessage = MessageModel(
                            text = chatListSnapshot.children.last().child("text").value.toString(),
                            senderId = chatListSnapshot.children.last()
                                .child("senderId").value.toString(),
                            time = chatListSnapshot.children.last()
                                .child("timestamp").value.toString()
                        )
                    )
                )
            } catch (e: Exception) {
                Log.e(TAG.FIREBASE, e.toString())
                onFailureAction()
            }
        }
        return chats
    }


    fun sendMessage(message: String) {

    }
}






