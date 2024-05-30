package android.skills_market.data.repository

import android.skills_market.data.constants.TAG
import android.skills_market.data.network.models.CompanyModel
import android.skills_market.data.network.models.VacancyModel
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore

interface FavouritesRepository {
    fun getLikedVacancies(
        onSuccessAction: (List<VacancyModel>) -> Unit,
        onFailureAction: () -> Unit
    )
}

class FirebaseFavouritesRepository(
):FavouritesRepository{
    override fun getLikedVacancies(
        onSuccessAction: (List<VacancyModel>) -> Unit,
        onFailureAction: () -> Unit
    ) {
        val vacancies = ArrayList<VacancyModel>()
        val likesRef = Firebase.database.getReference("likes").child(Firebase.auth.currentUser!!.uid)
        val collectionRef = Firebase.firestore.collection("vacancy")
        likesRef
            .get()
            .addOnSuccessListener {
                val likesIds = ArrayList<String>()
                for (data in it.children) {
                    likesIds.add(data.value.toString())
                }
                collectionRef
                    .get()
                    .addOnSuccessListener { documents ->
                        for (doc in documents) {
                            if (likesIds.contains(doc.data["id"].toString())) {
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
                                        liked = true
                                    )
                                )
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