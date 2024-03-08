package android.skills_market.app

import android.app.Application
import android.skills_market.data.AppContainer
import android.skills_market.data.DefaultAppContainer
import android.skills_market.network.SessionManager

class DefaultApplication : Application() {
    lateinit var container: AppContainer
    lateinit var sessionManager: SessionManager
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
        sessionManager = SessionManager(this)
    }
}