package android.skills_market.db_functions

import android.content.Context
import android.content.Intent
import android.skills_market.SearchActivity
import android.skills_market.users_dataclasses.Employer
import android.skills_market.users_dataclasses.Student
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern
import kotlin.concurrent.timerTask
import kotlin.math.log


class SMFirebase() {
    private val database = Firebase.database
    private fun init(): FirebaseDatabase {
        return Firebase.database
    }

    fun addUser(user: Student) {
        val rootRef = database.getReference("Student")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("MyTag", "OK")
                if (!dataSnapshot.exists()) {
                    Firebase.database.getReference("Student").push().setValue(user)
                    Log.d("MyTag", "Data added")
                    registration(user.email, user.password)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("ErrorTag", databaseError.message) //Don't ignore errors!
            }
        }
        rootRef.addListenerForSingleValueEvent(eventListener)
    }

    fun addUser(user: Employer) {
        val rootRef = database.getReference("Employer")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("MyTag", "OK")
                if (!dataSnapshot.exists()) {
                    Firebase.database.getReference("Employer").push().setValue(user)
                    Log.d("MyTag", "Data added")
                    registration(user.email, user.password)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("ErrorTag", databaseError.message) //Don't ignore errors!
            }
        }
        rootRef.addListenerForSingleValueEvent(eventListener)
    }

    fun loginUser(
        localContext: Context,
        email: String,
        password: String
    ) {
//        var rootRef = database.getReference("Student")
//        val valueEventListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (ds in dataSnapshot.children) {
//                    val stLogin = ds.child("email").getValue(String::class.java)
//                    val stPassword = ds.child("password").getValue(String::class.java)
//                    Log.w("WinTag", "checking for student")
//                    if (stLogin == login && stPassword == password) {
//                        Log.w("WinTag", "student found")
//                        localContext.startActivity(
//                            Intent(
//                                localContext,
//                                SearchActivity::class.java
//                            )
//                        )
//                    } else {
//                        Log.w("WinTag", "checking for employer")
//                        if (stLogin != login) {
//                            rootRef = database.getReference("Employer")
//                            val emLogin = ds.child("email").getValue(String::class.java)
//                            val emPassword = ds.child("password").getValue(String::class.java)
//                            if (emLogin != null && emPassword == password) {
//                                Log.w("WinTag", "employer found")
//                                localContext.startActivity(
//                                    Intent(
//                                        localContext,
//                                        SearchActivity::class.java
//                                    )
//                                )
//                            } else {
//                                Log.w("WinTag", "nothing here")
//                                Toast.makeText(localContext, "Зарегайся!", Toast.LENGTH_SHORT)
//                                    .show()
//                            }
//                        }
//                    }
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                Log.d("WinTag", databaseError.getMessage()) //Don't ignore errors!
//            }
//        }
//        val loginRef = rootRef.orderByChild("email").equalTo(login)
//        loginRef.addListenerForSingleValueEvent(valueEventListener)
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    localContext.startActivity(
                            Intent(
                                localContext,
                                SearchActivity::class.java
                            )
                        )
                } else {
                    Toast.makeText(localContext,"Login failed", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener(){
                Toast.makeText(localContext,"Login failed", Toast.LENGTH_SHORT).show()
            }
    }

    fun authentication(localContext: Context) {
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            /*TODO переход на активити если пользователь уже авторизован*/
            localContext.startActivity(
                Intent(
                    localContext,
                    SearchActivity::class.java
                )
            )
        }
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

private fun registration(email: String, password: String) {
    val auth = Firebase.auth
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("TagLog", "createUserWithEmail:success")
            } else {
                // If sign in fails, display a message to the user.
                Log.w("TagLog", "createUserWithEmail:failure", task.exception)
            }
        }
}




