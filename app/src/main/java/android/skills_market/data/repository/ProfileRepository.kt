package android.skills_market.data.repository

import android.skills_market.data.constants.TAG
import android.skills_market.data.network.models.StudentModel
import android.skills_market.data.network.models.UserAuthData
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

interface ProfileRepository {
    fun getUserInfo(
        currentUserId: String,
        onSuccessAction: (StudentModel) -> Unit,
        onFailureAction: () -> Unit
    )

    fun updateCurrentUserInfo(
        user:StudentModel,
        onFailureAction: () -> Unit
    )
}

class FirebaseProfileRepository(
) : ProfileRepository {
    override fun getUserInfo(
        currentUserId: String,
        onSuccessAction: (StudentModel) -> Unit,
        onFailureAction: () -> Unit
    ) {
        val usersDocRef = Firebase.firestore.collection("users").whereEqualTo("id", currentUserId)
        usersDocRef
            .get()
            .addOnSuccessListener {
                onSuccessAction(
                    StudentModel(
                        id = currentUserId,
                        userAuthData = UserAuthData(
                            it.last().data["userAuthData/email"].toString(),
                            currentUserId
                        ),
                        secondName = it.last().data["secondName"].toString(),
                        firstName = it.last().data["firstName"].toString(),
                        patronymicName = it.last().data["patronymicName"].toString(),
                        birthDate = it.last().data["birthDate"].toString(),
                        university = it.last().data["university"].toString(),
                        institute = it.last().data["institute"].toString(),
                        phoneNumber = it.last().data["phoneNumber"].toString(),
                        aboutMe = it.last().data["aboutMe"].toString(),
                        gender = it.last().data["gender"].toString(),
                        city = it.last().data["city"].toString(),
                        direction = it.last().data["direction"].toString(),
                        course = it.last().data["course"].toString(),
                    )
                )
            }
    }

    override fun updateCurrentUserInfo(
        user:StudentModel,
        onFailureAction: () -> Unit
    ) {
        val rootRef = Firebase.firestore.collection("users")
        rootRef.whereEqualTo("id", Firebase.auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener {
                val docId = it.last().id
                rootRef.document(docId)
                    .update(
                        mapOf(
                            "secondName" to user.secondName,
                            "patronymicName" to user.patronymicName,
                            "birthDate" to user.birthDate,
                            "university" to user.university,
                            "institute" to user.institute,
                            "phoneNumber" to user.phoneNumber,
                            "aboutMe" to user.aboutMe,
                            "gender" to user.gender,
                            "city" to user.city,
                            "direction" to user.direction,
                            "course" to user.course,
                        )
                    )
            }
            .addOnFailureListener {
                Log.e(TAG.FIREBASE, it.toString())
            }
    }
}