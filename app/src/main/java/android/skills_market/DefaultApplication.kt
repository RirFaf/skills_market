package android.skills_market

import android.app.Application
import android.skills_market.data.AppContainer
import android.skills_market.data.DefaultAppContainer

class DefaultApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}