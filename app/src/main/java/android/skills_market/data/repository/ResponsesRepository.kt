package android.skills_market.data.repository

import android.skills_market.data.constants.TAG
import android.skills_market.data.network.models.ChatModel
import android.skills_market.data.network.models.CompanyModel
import android.skills_market.data.network.models.MessageModel
import android.skills_market.data.network.models.VacancyModel
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore

interface ResponsesRepository {
    fun addChat(
        vacancyId: String,
        companyId: String,
        studentId: String,
    )

    fun getResponses(
        onSuccessAction: (List<VacancyModel>) -> Unit,
        onFailureAction: () -> Unit
    )
}

class FirebaseResponsesRepository(
) : ResponsesRepository {
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
                                            MessageModel(
                                                "Здавстуйте!",
                                                Firebase.auth.currentUser!!.uid,
                                                "0"
                                            ),
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

    override fun getResponses(
        onSuccessAction: (List<VacancyModel>) -> Unit,
        onFailureAction: () -> Unit
    ) {
        val vacancies = ArrayList<VacancyModel>()
        val responsesRef =
            Firebase.database.getReference("responses").child(Firebase.auth.currentUser!!.uid)
        val collectionRef = Firebase.firestore.collection("vacancy")
        responsesRef
            .get()
            .addOnSuccessListener {
                val responsesIds = ArrayList<String>()
                val responsesStatuses = ArrayList<String>()
                for (data in it.children) {
                    responsesIds.add(
                        data.child("vacancyId").value.toString()
                    )
                    responsesStatuses.add(
                        data.child("status").value.toString()
                    )
                }
                collectionRef
                    .get()
                    .addOnSuccessListener { documents ->
                        for (doc in documents) {
                            for (id in responsesIds.indices) {
                                if (responsesIds[id] == doc.data["id"].toString()) {
                                    vacancies.add(
                                        VacancyModel(
                                            id = doc.data["id"].toString(),
                                            company = CompanyModel(
                                                id = doc.data["companyId"].toString(),
                                                name = doc.data["companyName"].toString(),
                                            ),
                                            edArea = doc.data["edArea"].toString(),
                                            formOfEmployment = doc.data["formOfEmployment"].toString(),
                                            location = doc.data["location"].toString(),
                                            position = doc.data["position"].toString(),
                                            requirements = doc.data["requirements"].toString(),
                                            salary = doc.data["salary"].toString().toInt(),
                                            responseStatus = responsesStatuses[id]
                                        )
                                    )
                                }
                            }
                        }
                        onSuccessAction(vacancies)
                    }
            }
            .addOnFailureListener {
                onFailureAction()
                Log.e(TAG.FIREBASE, it.stackTraceToString())
            }
    }

}