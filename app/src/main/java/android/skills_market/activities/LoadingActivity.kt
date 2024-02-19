package android.skills_market.activities

import android.content.Intent
import android.os.Bundle
import android.skills_market.network.ApiClient
import android.skills_market.network.SMFirebase
import android.skills_market.network.SessionManager
import androidx.activity.ComponentActivity

class LoadingActivity : ComponentActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

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