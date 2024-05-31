package android.skills_market.data.repository

import android.skills_market.data.constants.TAG
import android.skills_market.data.network.models.CompanyModel
import android.skills_market.data.network.models.VacancyFilter
import android.skills_market.data.network.models.VacancyModel
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore

interface SearchRepository {
    fun changeLiked(
        vacancyId: String,
        onFailureAction: () -> Unit
    )

    fun getVacancies(
        search: String = "",
        filter: VacancyFilter,
        onSuccessAction: (List<VacancyModel>) -> Unit,
        onFailureAction: () -> Unit
    )

    fun respond(
        vacancyId: String,
        onFailureAction: () -> Unit
    )
}

class FirebaseSearchRepository(
) : SearchRepository {
    override fun changeLiked(
        vacancyId: String,
        onFailureAction: () -> Unit
    ) {
        SMFirebase.changeLiked(vacancyId, onFailureAction)
    }

    override fun respond(
        vacancyId: String,
        onFailureAction: () -> Unit
    ) {
        SMFirebase.respond(vacancyId, onFailureAction)
    }

    override fun getVacancies(
        search: String,
        filter: VacancyFilter,
        onSuccessAction: (List<VacancyModel>) -> Unit,
        onFailureAction: () -> Unit
    ) {
        val vacancies = ArrayList<VacancyModel>()
        val likesRef = Firebase.database.getReference("likes").child(Firebase.auth.currentUser!!.uid)
        //Поиск осуществляется тут
        var searchRef =
            if (search.isNotBlank()) {
                Firebase.firestore.collection("vacancy")
                    .whereEqualTo(
                        "companyName",
                        search.replaceFirstChar { it.uppercaseChar() })
            } else {
                Firebase.firestore.collection("vacancy")
            }

        //Фильтрация осуществляется тут
        var filterRef =
            when (filter) {
                is VacancyFilter.None -> {
                    searchRef
                }

                is VacancyFilter.BySalary -> {
                    searchRef
                        .whereGreaterThan("salary", filter.from)
                        .whereLessThan("salary", filter.to)
                }
            }

        filterRef.get()
            .addOnSuccessListener { documents ->
                for (doc in documents) {
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
                        )
                    )
                }
                likesRef
                    .get()
                    .addOnSuccessListener {
                        for (vacancy in vacancies) {
                            innerLoop@ for (data in it.children) {
                                if (data.value.toString() == vacancy.id) {
                                    vacancy.liked = true
                                    break@innerLoop
                                }
                            }
                        }
                        if (search.isBlank()) {
                            onSuccessAction(vacancies)
                        }
                    }
                    .addOnFailureListener {
                        Log.e(TAG.FIREBASE, it.stackTraceToString())
                    }
            }
            .addOnFailureListener {
                onFailureAction()
                Log.e(TAG.FIREBASE, it.toString())
            }



        if (search.isNotBlank()) {
            searchRef =
                if (search.isNotBlank()) {
                    Firebase.firestore.collection("vacancy")
                        .whereEqualTo(
                            "position",
                            search.replaceFirstChar { it.uppercaseChar() })
                } else {
                    Firebase.firestore.collection("vacancy")
                }

            filterRef =
                when (filter) {
                    is VacancyFilter.None -> {
                        searchRef
                    }

                    is VacancyFilter.BySalary -> {
                        searchRef
                            .whereGreaterThan("salary", filter.from)
                            .whereLessThan("salary", filter.to)
                    }
                }

            filterRef.get()
                .addOnSuccessListener { documents ->
                    for (doc in documents) {
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
                            )
                        )
                    }
                    likesRef
                        .get()
                        .addOnSuccessListener {
                            for (vacancy in vacancies) {
                                innerLoop@ for (data in it.children) {
                                    if (data.value.toString() == vacancy.id) {
                                        vacancy.liked = true
                                        break@innerLoop
                                    }
                                }
                            }
                            onSuccessAction(vacancies)
                        }
                        .addOnFailureListener {
                            Log.e(TAG.FIREBASE, it.stackTraceToString())
                        }
                }
                .addOnFailureListener {
                    onFailureAction()
                    Log.e(TAG.FIREBASE, it.toString())
                }
        }
    }
}