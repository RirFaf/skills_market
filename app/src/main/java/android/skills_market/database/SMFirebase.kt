package android.skills_market.database

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.skills_market.data.StudentModel
import android.skills_market.ui.activities.AppActivity
import android.skills_market.ui.activities.LogRegActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern
import kotlin.Exception


class SMFirebase() {
    private val db = Firebase.database

    fun addUser(
        user: StudentModel,
        onSuccessAction: () -> Unit,
    ) {
        var e: Exception?
        val rootRef = Firebase.database.getReference("Student")
        val auth = Firebase.auth
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                rootRef.push().setValue(user)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("ErrorTag", databaseError.message) //Don't ignore errors!
            }
        }
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                rootRef.addListenerForSingleValueEvent(eventListener)
                onSuccessAction()
            }
            .addOnFailureListener() { task ->
                e = task
                Log.e("ErrorTag", e.toString())
            }
    }

    /* TODO: Исправить валидацию */
    @Throws(Exception::class)
    fun loginUser(
        onSuccessAction: () -> Unit,
        login: String,
        password: String
    ) {
        var e: Exception? = null
        val auth = Firebase.auth

        auth.signInWithEmailAndPassword(login, password)
            .addOnSuccessListener {
                onSuccessAction()
            }
            .addOnFailureListener() { task ->
                e = task
                Log.e("ErrorTag", e.toString())
            }
            .addOnCompleteListener { task ->
                e = task.exception!!
            }
        Log.e("ErrorTag", e.toString())
        if (e != null) {
            throw e!!
        }
    }

    fun logoutUser(localContext: Context) {
        val auth = Firebase.auth
        auth.signOut()
        (localContext as Activity).finish()
        localContext.startActivity(
            Intent(
                localContext,
                LogRegActivity::class.java
            )
        )
    }

    fun authenticateUser(localContext: Context) {
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            (localContext as Activity).finish()
            localContext.startActivity(
                Intent(
                    localContext,
                    AppActivity::class.java
                )
            )
        } else {
            (localContext as Activity).finish()
            localContext.startActivity(
                Intent(
                    localContext,
                    LogRegActivity::class.java
                )
            )
        }
    }

    fun isEmailValid(email: String): Boolean {
        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"
        )
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }
}






