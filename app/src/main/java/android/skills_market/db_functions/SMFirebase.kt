package android.skills_market.db_functions

import android.content.Context
import android.content.Intent
import android.skills_market.SearchActivity
import android.skills_market.dataclasses.Employer
import android.skills_market.dataclasses.Student
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.currentCompositionLocalContext
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class SMFirebase() {
    private val database = Firebase.database

    fun addUser(user: Student) {
        val rootRef = database.getReference("User")
        val auth = Firebase.auth
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("MyTag", "OK")
                rootRef.child("Student").push().setValue(user)
                Log.d("MyTag", "Data added")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("ErrorTag", databaseError.message) //Don't ignore errors!
            }
        }
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener()
            { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TagLog", "createUserWithEmail:success")
                    rootRef.addListenerForSingleValueEvent(eventListener)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TagLog", "createUserWithEmail:failure", task.exception)
                }
            }
    }

    fun addUser(user: Employer) {
        val rootRef = database.getReference("User")
        val auth = Firebase.auth
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("MyTag", "OK")
                rootRef.child("Employer").push().setValue(user)
                Log.d("MyTag", "Data added")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("ErrorTag", databaseError.message) //Don't ignore errors!
            }
        }
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener()
            { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TagLog", "createUserWithEmail:success")
                    rootRef.addListenerForSingleValueEvent(eventListener)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TagLog", "createUserWithEmail:failure", task.exception)
                }
            }
    }

    fun loginUser(
        localContext: Context,
        email: String,
        password: String
    ) {
        val rootRef = database.getReference("User")
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val stLogin = ds.child("Student").child("email").getValue(String::class.java)
                    val stPassword =
                        ds.child("Student").child("password").getValue(String::class.java)
                    val emLogin = ds.child("Employer").child("email").getValue(String::class.java)
                    val emPassword =
                        ds.child("Employer").child("password").getValue(String::class.java)
                    if ((stLogin == email && stPassword == password) || (emLogin == email && emPassword == password)) {
                        Log.w("WinTag", stLogin.toString())
                        Log.w("WinTag", emLogin.toString())
                        localContext.startActivity(
                            Intent(
                                localContext,
                                SearchActivity::class.java
                            )
                        )
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("WinTag", databaseError.getMessage()) //Don't ignore errors!
            }
        }

        val loginRef = rootRef.orderByChild("email").equalTo(email)
        loginRef.addListenerForSingleValueEvent(valueEventListener)

        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener()
            { task ->
                if (task.isSuccessful) {
                    localContext.startActivity(
                        Intent(
                            localContext,
                            SearchActivity::class.java
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




