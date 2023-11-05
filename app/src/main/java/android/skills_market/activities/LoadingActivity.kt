package android.skills_market.activities

import android.content.Intent
import android.os.Bundle
import android.skills_market.network.SMFirebase
import androidx.activity.ComponentActivity

class LoadingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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