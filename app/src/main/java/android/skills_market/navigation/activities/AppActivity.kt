package android.skills_market.navigation.activities

import android.os.Bundle
import android.skills_market.navigation.common_classes.Navigation
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class AppActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Navigation(this)
        }
    }
}