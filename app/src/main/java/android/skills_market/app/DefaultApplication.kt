package android.skills_market.app

import android.app.Application
import android.skills_market.data.RepositoryContainer
import android.skills_market.data.DefaultRepositoryContainer
import android.skills_market.data.network.SessionManager

class DefaultApplication : Application() {
    lateinit var container: RepositoryContainer
    lateinit var sessionManager: SessionManager
    override fun onCreate() {
        super.onCreate()
        container = DefaultRepositoryContainer()
        sessionManager = SessionManager(this)
    }
}
