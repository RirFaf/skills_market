package android.skills_market.data.network

import android.skills_market.data.network.models.StudentModel
import android.skills_market.data.network.models.UserAuthData
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object SMFirebase {
    private const val tag = "FirebaseTag"
    private val auth = Firebase.auth

    suspend fun addUser(
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
                Log.e(tag, it.toString())
                onFailureAction()
            }
    }

    suspend fun updateCurrentUserInfo(
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
                    Log.e(tag, it.toString())
                }
                .addOnSuccessListener {
                    Log.d(tag, "User info updated")
                }
        }
    }

    suspend fun loginUser(
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
                Log.e(tag, it.toString())
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
    fun isPersonalInfoBlank(): Boolean {
        val collectionRef = Firebase.firestore.collection("users")
        var res = true
        if (auth.currentUser != null) {
            collectionRef.document(
                collectionRef.whereEqualTo("id", auth.currentUser?.uid).get().result.documents[0].id
            ).get()
                .addOnFailureListener {
                    Log.e(tag, it.toString())
                    res = true
                }
                .addOnSuccessListener { document ->
                    res = if (document != null) {
                        !(document.get("firstName").toString().isBlank() &&
                                document.get("secondName").toString().isBlank() &&
                                document.get("patronymicName").toString().isBlank() &&
                                document.get("gender").toString().isBlank() &&
                                document.get("birthDate").toString().isBlank())
                    } else {
                        true
                    }
                }
        } else {
            res = true
        }
        return res
    }

    fun isUniversityInfoBlank(): Boolean {
        val collectionRef = Firebase.firestore.collection("users")
        var res = true
        if (auth.currentUser != null) {
            collectionRef.document(
                collectionRef.whereEqualTo("id", auth.currentUser?.uid).get().result.documents[0].id
            ).get()
                .addOnFailureListener {
                    Log.e(tag, it.toString())
                    res = true
                }
                .addOnSuccessListener { document ->
                    res = if (document != null) {
                        !(document.get("city").toString().isBlank() &&
                                document.get("direction").toString().isBlank() &&
                                document.get("university").toString().isBlank() &&
                                document.get("institute").toString().isBlank())

                    } else {
                        true
                    }
                }
        } else {
            res = true
        }
        return res
    }
}






