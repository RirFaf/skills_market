package android.skills_market.db_functions

import android.content.Context
import android.skills_market.dataclasses.StudentModel
import android.skills_market.navigation.common_classes.Screen
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class SMFirebase() {
    private val database = Firebase.database
    fun addUser(
        localContext: Context,
        navController: NavController,
        user: StudentModel
    ) {
        val rootRef = database.getReference("Student")
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
                        rootRef.addListenerForSingleValueEvent(eventListener)
                        navController.navigate(Screen.SearchScreen.route)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            localContext,
                            "//TODO: сделать валидацию",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
        } else {
            Toast.makeText(
                localContext,
                "//TODO: сделать валидацию",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    fun loginUser(
        localContext: Context,
        navController: NavController,
        login: String,
        password: String
    ) {
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener()
            { task ->
                if (task.isSuccessful) {
                    navController.navigate(Screen.SearchScreen.route)
                } else {
                    Toast.makeText(localContext, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener()
            {
                Toast.makeText(localContext, "Login failed", Toast.LENGTH_SHORT).show()
            }
    }

    fun isAuthenticated(): Boolean {
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        return currentUser != null
    }

//   TODO: fun logout()

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





