package android.skills_market.navigation.activities

import android.os.Bundle
import android.skills_market.navigation.common_classes.NavigationGraph
import android.skills_market.navigation.views.MainScreenView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenView(localContext = this)
        }
    }
}