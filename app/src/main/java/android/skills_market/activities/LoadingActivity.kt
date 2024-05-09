package android.skills_market.activities

import android.content.Intent
import android.os.Bundle
import android.skills_market.data.network.SMFirebase
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import kotlin.coroutines.CoroutineContext

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