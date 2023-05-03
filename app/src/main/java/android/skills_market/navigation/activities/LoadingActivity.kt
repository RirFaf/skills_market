package android.skills_market.navigation.activities

import android.content.Intent
import android.os.Bundle
import android.skills_market.R
import android.skills_market.db_functions.SMFirebase
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
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
        setContent {
            val scale = remember {
                androidx.compose.animation.core.Animatable(0f)
            }
            val database = SMFirebase()
            // Animation
            LaunchedEffect(key1 = true) {
                scale.animateTo(
                    targetValue = 0.7f,
                    // tween Animation
                    animationSpec = tween(
                        durationMillis = 800,
                        easing = {
                            OvershootInterpolator(4f).getInterpolation(it)
                        })
                )
                delay(1000L)

                database.authentication(this@LoadingActivity)
            }

            // Image
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                // Change the logo
                Image(
                    painter = painterResource(id = R.drawable.gex),
                    contentDescription = "Logo",
                    modifier = Modifier.scale(scale.value)
                )
            }
        }
    }
}