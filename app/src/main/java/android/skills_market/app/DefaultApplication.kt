package android.skills_market.app

import android.app.Application
import android.skills_market.data.RepositoryContainer
import android.skills_market.data.DefaultRepositoryContainer
import android.skills_market.data.network.SMFirebase
import android.skills_market.data.network.SessionManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DefaultApplication : Application() {
    lateinit var container: RepositoryContainer
    lateinit var sessionManager: SessionManager
    lateinit var currentUserId: String
    override fun onCreate() {
        super.onCreate()
        container = DefaultRepositoryContainer()
        sessionManager = SessionManager(this)
        currentUserId = Firebase.auth.currentUser?.uid.toString()
    }
}
