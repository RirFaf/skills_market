package android.skills_market.ui.screens.resume

import android.skills_market.view_model.ResumeUIState
import android.skills_market.view_model.event.ResumeEvent
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ResumeRedactorScreen(
    state: ResumeUIState.Success,//TODO убрать Success
    navController: NavController,
    onEvent: (ResumeEvent) -> Unit
) {

}