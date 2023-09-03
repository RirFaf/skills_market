package android.skills_market.ui.activities

import android.content.Intent
import android.os.Bundle
import android.skills_market.R
import android.skills_market.database.SMFirebase
import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

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