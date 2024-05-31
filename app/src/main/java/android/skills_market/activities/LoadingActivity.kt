package android.skills_market.activities

import android.content.Intent
import android.os.Bundle
import android.skills_market.data.repository.SMFirebase
import androidx.activity.ComponentActivity

class LoadingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = SMFirebase
        val isUserAuthenticated = database.authenticateUser()
        intent =
            if (isUserAuthenticated) {
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

        this.startActivity(intent)
        this.finish()
    }
}