package android.skills_market.data.repository

import android.skills_market.data.constants.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

interface LoginRepository {
    fun login(
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
        login: String,
        password: String
    )
}

class FirebaseLoginRepository(
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


