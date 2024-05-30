package android.skills_market.data.repository

import android.skills_market.data.constants.TAG
import android.skills_market.data.network.AuthApiService
import android.skills_market.data.network.SMFirebase
import android.skills_market.data.network.models.StudentModel
import android.skills_market.data.network.models.UserAuthData
import android.skills_market.data.network.models.requests.AuthRequest
import android.skills_market.data.network.models.responses.AuthResponse
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import retrofit2.Call

interface RegistrationRepository {
    suspend fun register(
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
    )
}

class NetworkRegistrationRepository() : RegistrationRepository {
    override suspend fun register(
        login: String,
        password: String,
        secondName: String,
        firstName: String,
        patronymicName: String,
        birthDate: String,
        university: String,
        institute: String,
        phoneNumber: String,
        aboutMe: String,
        gender: String,
        city: String,
        direction: String,
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
    ) {
        val rootRef = Firebase.firestore.collection("users")
        Firebase.auth.createUserWithEmailAndPassword(login, password)
            .addOnSuccessListener {
                Firebase.auth.currentUser?.let { currentUser ->
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
}