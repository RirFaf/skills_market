package android.skills_market.activities

import android.content.Intent
import android.os.Bundle
import android.skills_market.data.network.AuthApiClient
import android.skills_market.data.network.SMFirebase
import androidx.activity.ComponentActivity

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