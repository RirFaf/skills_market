package android.skills_market.data.repository

import android.skills_market.data.constants.ResponseStatus
import android.skills_market.data.constants.TAG
import android.skills_market.data.network.models.ResumeModel
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore

object SMFirebase {
    private val auth = Firebase.auth
    private val currentUserId = Firebase.auth.currentUser?.uid

    fun respond(
        vacancyId: String,
        companyId: String,
        onFailureAction: () -> Unit
    ) {
        val responsesRef =
            Firebase.database.getReference("responses").child(Firebase.auth.currentUser!!.uid)
        var alreadyAdded = false
        responsesRef
            .get()
            .addOnSuccessListener {
                for (data in it.children) {
                    if (data.child("vacancyId").value == vacancyId) {
                        alreadyAdded = true
                        break
                    }
                }
                if (!alreadyAdded) {
                    responsesRef
                        .push()
                        .setValue(
                            mapOf(
                                "vacancyId" to vacancyId,
                                "companyId" to companyId,
                                "status" to ResponseStatus.SENT
                            )
                        )
                }
            }
            .addOnFailureListener {
                onFailureAction()
                Log.e(TAG.FIREBASE, it.stackTraceToString())
            }
    }

    fun changeLiked(
        vacancyId: String,
        onFailureAction: () -> Unit
    ) {
        val likesRef =
            Firebase.database.getReference("likes").child(Firebase.auth.currentUser!!.uid)
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

    fun createResume(
    ) {
        Firebase.firestore.collection("resumeAndroid").add(
            ResumeModel(
                studentId = Firebase.auth.currentUser!!.uid,
                keySkills = "",
                salary = ""
            )
        )
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
}






