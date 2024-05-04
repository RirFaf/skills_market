package android.skills_market.data.network

import android.skills_market.data.network.models.StudentModel
import android.skills_market.data.network.models.UserAuthData
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object SMFirebase {
    private const val tag = "FirebaseTag"
    private val auth = Firebase.auth

    fun addUser(
        login: String,
        password: String,
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
    ) {
        Log.d(tag, "addUser")
        val rootRef = Firebase.firestore.collection("users")

        auth.createUserWithEmailAndPassword(login, password)
            .addOnSuccessListener {
                auth.currentUser?.let { currentUser ->
                    rootRef.add(
                        StudentModel(
                            userAuthData = UserAuthData(login, currentUser.uid),
                            firstName = "",
                            secondName = "",
                            patronymicName = "",
                            birthDate = "",
                            university = "",
                            institute = "",
                            phoneNumber = "",
                            aboutMe = "",
                            gender = "",
                            city = "",
                            direction = "",
                            id = currentUser.uid
                        )
                    )
                    Log.d(tag, "User created in db")
                }
                Log.i(tag, "${auth.currentUser}")
                auth.currentUser?.let { Log.i(tag, "User created successfully\nid=${it.uid}") }
                onSuccessAction()
            }
            .addOnFailureListener {
                Log.e(tag, it.toString())
                onFailureAction()
            }
    }

    fun updateUserInfo(
        user: StudentModel
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
                    "firstName" to user.firstName,
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
                    "id" to currentUser.uid
                )
            )
                .addOnFailureListener {
                Log.e(tag, it.toString())
            }
                .addOnSuccessListener {
                    Log.d(tag,"User info updated")
                }
        }
    }


    /* TODO: Исправить валидацию (поля меняют цвет в любом случае) */
    fun loginUser(
        onSuccessAction: () -> Unit,
        login: String,
        password: String
    ) {
        auth.signInWithEmailAndPassword(login, password)
            .addOnSuccessListener {
                onSuccessAction()
            }
            .addOnFailureListener {
                Log.e(tag, it.toString())
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
}






