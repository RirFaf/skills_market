package android.skills_market.data.repository

import android.skills_market.data.constants.TAG
import android.skills_market.data.network.models.ResumeModel
import android.skills_market.data.network.models.StudentModel
import android.skills_market.data.network.models.UserAuthData
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

interface ResumeRepository {
    fun getResume(
        currentUserId:String,
        onSuccessAction: (ResumeModel) -> Unit,
        onFailureAction: () -> Unit
    )
    fun updateResume(
        resume: ResumeModel,
        onFailureAction: () -> Unit
    )
}

class FirebaseResumeRepository(
):ResumeRepository{
    override fun getResume(
        currentUserId: String,
        onSuccessAction: (ResumeModel) -> Unit,
        onFailureAction: () -> Unit
    ) {
        Firebase.firestore
            .collection("resumeAndroid")
            .whereEqualTo("studentId", currentUserId)
            .get()
            .addOnSuccessListener {
                onSuccessAction(
                    ResumeModel(
                        studentId = currentUserId,
                        keySkills = it.last().data["keySkills"].toString(),
                        salary = it.last().data["salary"].toString(),
                    )
                )
            }
            .addOnFailureListener {
                Log.e(TAG.FIREBASE, it.stackTraceToString())
            }
    }

    override fun updateResume(
        resume: ResumeModel,
        onFailureAction: () -> Unit
    ) {
        val rootRef = Firebase.firestore.collection("resumeAndroid")
        rootRef.whereEqualTo("studentId", Firebase.auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener {
                val docId = it.last().id
                rootRef.document(docId)
                    .update(
                        mapOf(
                            "keySkills" to resume.keySkills,
                            "salary" to resume.salary,
                        )
                    )
            }
            .addOnFailureListener {
                Log.e(TAG.FIREBASE, it.toString())
            }
    }

}