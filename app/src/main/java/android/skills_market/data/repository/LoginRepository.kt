package android.skills_market.data.repository

import android.skills_market.data.constants.TAG
import android.skills_market.data.network.AuthApiService
import android.skills_market.data.network.SMFirebase
import android.skills_market.data.network.models.requests.AuthRequest
import android.skills_market.data.network.models.responses.AuthResponse
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import retrofit2.Call
import retrofit2.Response

interface LoginRepository {
    fun login(
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
        login: String,
        password: String
    )
}

class NetworkLoginRepository(
) : LoginRepository {
    override fun login(
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
        login: String,
        password: String
    ) {
        Firebase.auth.signInWithEmailAndPassword(login, password)
            .addOnSuccessListener {
                onSuccessAction()
            }
            .addOnFailureListener {
                Log.e(TAG.FIREBASE, it.toString())
                onFailureAction()
            }
    }
}


