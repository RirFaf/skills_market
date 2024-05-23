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


    fun getVacancies(
        search: String = "",
        filter: VacancyFilter,
        onSuccessAction: (List<VacancyModel>) -> Unit,
        onFailureAction: () -> Unit
    ) {
        val vacancies = ArrayList<VacancyModel>()
        //Поиск осуществляется тут
        var searchRef =
            if (search.isNotBlank()) {
                Log.d(TAG.FIREBASE, "searching vacancies")
                Firebase.firestore.collection("vacancy")
                    .whereEqualTo(
                        "companyName",
                        search.replaceFirstChar { it.uppercaseChar() })
            } else {
                Log.d(TAG.FIREBASE, "loading all vacancies")
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
                Log.d(TAG.FIREBASE, "filter = ${filter.javaClass.canonicalName}, search = $search")
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
                            liked = doc.data["liked"].toString().toBoolean(),
                            location = doc.data["location"].toString(),
                            position = doc.data["position"].toString(),
                            requirements = doc.data["requirements"].toString(),
                            salary = doc.data["salary"].toString().toInt()
                        )
                    )
                }
                onSuccessAction(vacancies)
            }
            .addOnFailureListener {
                onFailureAction()
                Log.e(TAG.FIREBASE, it.toString())
            }
        if (search.isNotBlank()) {
            //Поиск осуществляется тут
            searchRef =
                    Firebase.firestore.collection("vacancy")
                        .whereEqualTo(
                            "position",
                            search.replaceFirstChar { it.uppercaseChar() })
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
                    Log.d(
                        TAG.FIREBASE,
                        "filter = ${filter.javaClass.canonicalName}, search = $search"
                    )
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
                                liked = doc.data["liked"].toString().toBoolean(),
                                location = doc.data["location"].toString(),
                                position = doc.data["position"].toString(),
                                requirements = doc.data["requirements"].toString(),
                                salary = doc.data["salary"].toString().toInt()
                            )
                        )
                    }
                    onSuccessAction(vacancies)
                }
                .addOnFailureListener {
                    onFailureAction()
                    Log.e(TAG.FIREBASE, it.toString())
                }
        }
    }

    fun addUser(
        login: String,
        password: String,
        secondName: String,
        firstName: String,
        patronymicName: String,
        birthDate: String,
        university: String,
        institute: String,
        phoneNumber: String = "",
        aboutMe: String = "",
        gender: String,
        city: String,
        direction: String,
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
    ) {
        val rootRef = Firebase.firestore.collection("users")
        auth.createUserWithEmailAndPassword(login, password)
            .addOnSuccessListener {
                auth.currentUser?.let { currentUser ->
                    rootRef.add(
                        StudentModel(
                            userAuthData = UserAuthData(login, currentUser.uid),
                            firstName = firstName,
                            secondName = secondName,
                            patronymicName = patronymicName,
                            birthDate = birthDate,
                            university = university,
                            institute = institute,
                            phoneNumber = phoneNumber,
                            aboutMe = aboutMe,
                            gender = gender,
                            city = city,
                            direction = direction,
                            id = currentUser.uid
                        )
                    )
                }
                onSuccessAction()
            }
            .addOnFailureListener {
                Log.e(TAG.FIREBASE, it.toString())
                onFailureAction()
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

    fun loginUser(
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
        login: String,
        password: String
    ) {
        auth.signInWithEmailAndPassword(login, password)
            .addOnSuccessListener {
                onSuccessAction()
            }
            .addOnFailureListener {
                Log.e(TAG.FIREBASE, it.toString())
                onFailureAction()
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

    suspend fun getMessages(
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


    suspend fun sendMessage(message: String) {

    }
}






