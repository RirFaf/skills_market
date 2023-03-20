package android.skills_market.db_functions

import android.skills_market.users_dataclasses.Employer
import android.skills_market.users_dataclasses.Student
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SMFirebase() {
    fun init(DB_KEY: String): DatabaseReference {
        return Firebase.database.getReference(DB_KEY)
    }

    fun addUser(DB_KEY: String, user: Student) {
        Firebase.database.getReference(DB_KEY).push().setValue(user)
    }

    fun addUser(DB_KEY: String, user: Employer) {
        Firebase.database.getReference(DB_KEY).push().setValue(user)
    }

}