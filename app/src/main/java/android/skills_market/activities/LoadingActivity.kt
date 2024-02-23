package android.skills_market.activities

import android.content.Intent
import android.os.Bundle
import android.skills_market.network.AuthApiClient
import android.skills_market.network.SMFirebase
import android.skills_market.network.SessionManager
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData

class LoadingActivity : ComponentActivity() {
    private lateinit var authApiClient: AuthApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authApiClient = AuthApiClient()
        val database = SMFirebase()

        intent = if (database.authenticateUser()) {
            Intent(
                this,
                AppActivity::class.java
            )
        } else {
            Intent(
                this,
                LogRegActivity::class.java
            )
        }

        this.finish()
        this.startActivity(intent)
    }
}