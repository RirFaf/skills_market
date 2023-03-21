package android.skills_market.db_functions

import android.content.Context
import android.content.Intent
import android.skills_market.SearchActivity
import android.skills_market.users_dataclasses.Employer
import android.skills_market.users_dataclasses.Student
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class SMFirebase() {
    fun init(DB_KEY: String): DatabaseReference {
        return Firebase.database.getReference(DB_KEY)
    }

    fun addStudent(user: Student) {
        val rootRef = Firebase.database.getReference("Student")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("MyTag", "OK")
                if (!dataSnapshot.exists()) {
                    Firebase.database.getReference("Student").push().setValue(user)
                    Log.d("MyTag", "Data added")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("ErrorTag", databaseError.message) //Don't ignore errors!
            }
        }
        rootRef.addListenerForSingleValueEvent(eventListener)
    }

    fun addEmployer(user: Employer) {
        val rootRef = Firebase.database.getReference("Employer")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("MyTag", "OK")
                if (!dataSnapshot.exists()) {
                    Firebase.database.getReference("Employer").push().setValue(user)
                    Log.d("MyTag", "Data added")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("ErrorTag", databaseError.message) //Don't ignore errors!
            }
        }
        rootRef.addListenerForSingleValueEvent(eventListener)
    }


    private fun loginUser(
        localContext: Context,
        login: String,
        employer: Employer
    ) {

        val rootRef = Firebase.database.getReference("Employer")
        val loginRef = rootRef.orderByChild("email").equalTo(login)
        Log.d("Tag", loginRef.toString())


        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val username = ds.child("username").getValue(String::class.java)
                    Log.d("MyTag", "")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("MyTag", databaseError.getMessage()) //Don't ignore errors!
            }
        }
        loginRef.addListenerForSingleValueEvent(valueEventListener)
        if (isEmailValid(login)) {
            localContext.startActivity(
                Intent(
                    localContext,
                    SearchActivity::class.java
                )
            )
        }
    }
    fun loginUser(
        localContext: Context,
        login: String
    ) {
//        val rootRef = Firebase.database.getReference("Student")
//        val loginRef = rootRef.orderByChild("email").equalTo(login)
//
//        val valueEventListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (ds in dataSnapshot.children) {
//                    val username = ds.child("name").getValue(String::class.java)
//                    Log.d("LogTag", username.toString())
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                Log.d("MyTag", databaseError.getMessage()) //Don't ignore errors!
//            }
//        }
//        loginRef.addListenerForSingleValueEvent(valueEventListener)
        if (isEmailValid(login)) {
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
