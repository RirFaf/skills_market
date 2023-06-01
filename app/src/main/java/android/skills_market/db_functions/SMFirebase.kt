package android.skills_market.db_functions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.skills_market.dataclasses.StudentModel
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


class SMFirebase() {
    private val database = Firebase.database
    fun addUser(
        localContext: Context,
        user: StudentModel
    ) {
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
        if (user.phone.length == 11) {
            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener()
                { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TagLog", "createUserWithEmail:success")
                        val userId = auth.currentUser!!.uid
//                        rootRef.child(userId).setValue(user)
                        rootRef.addListenerForSingleValueEvent(eventListener)
                        (localContext as Activity).finish()
                        localContext.startActivity(
                            Intent(
                                localContext,
                                AppActivity::class.java
                            )
                        )
                    } else {
                        try {
                        } catch (e: FirebaseAuthWeakPasswordException){
                            Toast.makeText(localContext, "Пароль должен содержать минимум 6 символов", Toast.LENGTH_SHORT).show()
                        }catch (e: FirebaseAuthInvalidCredentialsException){
                            Toast.makeText(localContext, "Введите верный адрес эл. почты", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        } else {
            Toast.makeText(localContext, "Введите верный номер телефона", Toast.LENGTH_SHORT).show()
        }
    }

    fun loginUser(
        localContext: Context,
        login: String,
        password: String
    ) {
        val auth = Firebase.auth
        Log.d("MyTag", auth.currentUser.toString())
        auth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener()
            { task ->
                if (task.isSuccessful) {
                    (localContext as Activity).finish()
                    localContext.startActivity(
                        Intent(
                            localContext,
                            AppActivity::class.java
                        )
                    )
                } else {
                    Toast.makeText(localContext, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener()
            {
                Toast.makeText(localContext, "Login failed", Toast.LENGTH_SHORT).show()
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





